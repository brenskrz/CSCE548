package musicdb1.music_service;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class SongDAO {

    public Song create(Song song) throws SQLException {
        String sql = "INSERT INTO Song (title, duration_seconds, release_date, genre, album_id) VALUES (?, ?, ?, ?, ?)";

        try (Connection conn = DBUtil.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS)) {

            stmt.setString(1, song.getTitle());
            stmt.setInt(2, song.getDurationSeconds());
            if (song.getReleaseDate() != null) {
                stmt.setDate(3, Date.valueOf(song.getReleaseDate()));
            } else {
                stmt.setNull(3, Types.DATE);
            }
            stmt.setString(4, song.getGenre());
            stmt.setInt(5, song.getAlbumId());

            int affected = stmt.executeUpdate();
            if (affected == 0) throw new SQLException("Creating song failed, no rows affected.");

            try (ResultSet keys = stmt.getGeneratedKeys()) {
                if (keys.next()) {
                    song.setSongId(keys.getInt(1));
                    return song;
                } else {
                    throw new SQLException("Creating song failed, no ID obtained.");
                }
            }
        }
    }

    public void update(Song song) throws SQLException {
        String sql = "UPDATE Song SET title=?, duration_seconds=?, release_date=?, genre=?, album_id=? WHERE song_id=?";
        try (Connection conn = DBUtil.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql)) {

            stmt.setString(1, song.getTitle());
            stmt.setInt(2, song.getDurationSeconds());
            if (song.getReleaseDate() != null) {
                stmt.setDate(3, Date.valueOf(song.getReleaseDate()));
            } else {
                stmt.setNull(3, Types.DATE);
            }
            stmt.setString(4, song.getGenre());
            stmt.setInt(5, song.getAlbumId());
            stmt.setInt(6, song.getSongId());

            stmt.executeUpdate();
        }
    }

    public void delete(int id) throws SQLException {
        String sql = "DELETE FROM Song WHERE song_id=?";
        try (Connection conn = DBUtil.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql)) {
            stmt.setInt(1, id);
            stmt.executeUpdate();
        }
    }

    public Song readById(int id) throws SQLException {
        String sql = "SELECT * FROM Song WHERE song_id=?";
        try (Connection conn = DBUtil.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql)) {
            stmt.setInt(1, id);
            try (ResultSet rs = stmt.executeQuery()) {
                if (rs.next()) {
                    Date d = rs.getDate("release_date");
                    return new Song(
                            rs.getInt("song_id"),
                            rs.getString("title"),
                            rs.getInt("duration_seconds"),
                            d != null ? d.toString() : null,
                            rs.getString("genre"),
                            rs.getInt("album_id")
                    );
                }
            }
        }
        return null;
    }

    public List<Song> readAll() throws SQLException {
        List<Song> list = new ArrayList<>();
        String sql = "SELECT * FROM Song";
        try (Connection conn = DBUtil.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql);
             ResultSet rs = stmt.executeQuery()) {
            while (rs.next()) {
                Date d = rs.getDate("release_date");
                list.add(new Song(
                        rs.getInt("song_id"),
                        rs.getString("title"),
                        rs.getInt("duration_seconds"),
                        d != null ? d.toString() : null,
                        rs.getString("genre"),
                        rs.getInt("album_id")
                ));
            }
        }
        return list;
    }
}