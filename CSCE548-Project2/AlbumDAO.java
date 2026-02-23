package musicdb1.music_service;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class AlbumDAO {

    public Album create(Album album) throws SQLException {
        String sql = "INSERT INTO Album (title, release_date, song_count, genre, artist_id) VALUES (?, ?, ?, ?, ?)";

        try (Connection conn = DBUtil.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS)) {

            stmt.setString(1, album.getTitle());
            if (album.getReleaseDate() != null) {
                stmt.setDate(2, Date.valueOf(album.getReleaseDate()));
            } else {
                stmt.setNull(2, Types.DATE);
            }
            stmt.setInt(3, album.getSongCount());
            stmt.setString(4, album.getGenre());
            stmt.setInt(5, album.getArtistId());

            int affected = stmt.executeUpdate();
            if (affected == 0) throw new SQLException("Creating album failed, no rows affected.");

            try (ResultSet keys = stmt.getGeneratedKeys()) {
                if (keys.next()) {
                    album.setAlbumId(keys.getInt(1));
                    return album;
                } else {
                    throw new SQLException("Creating album failed, no ID obtained.");
                }
            }
        }
    }

    public void update(Album album) throws SQLException {
        String sql = "UPDATE Album SET title=?, release_date=?, song_count=?, genre=?, artist_id=? WHERE album_id=?";
        try (Connection conn = DBUtil.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql)) {

            stmt.setString(1, album.getTitle());
            if (album.getReleaseDate() != null) {
                stmt.setDate(2, Date.valueOf(album.getReleaseDate()));
            } else {
                stmt.setNull(2, Types.DATE);
            }
            stmt.setInt(3, album.getSongCount());
            stmt.setString(4, album.getGenre());
            stmt.setInt(5, album.getArtistId());
            stmt.setInt(6, album.getAlbumId());

            stmt.executeUpdate();
        }
    }

    public void delete(int id) throws SQLException {
        String sql = "DELETE FROM Album WHERE album_id=?";
        try (Connection conn = DBUtil.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql)) {
            stmt.setInt(1, id);
            stmt.executeUpdate();
        }
    }

    public Album readById(int id) throws SQLException {
        String sql = "SELECT * FROM Album WHERE album_id=?";
        try (Connection conn = DBUtil.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql)) {
            stmt.setInt(1, id);
            try (ResultSet rs = stmt.executeQuery()) {
                if (rs.next()) {
                    Date d = rs.getDate("release_date");
                    return new Album(
                            rs.getInt("album_id"),
                            rs.getString("title"),
                            d != null ? d.toString() : null,
                            rs.getInt("song_count"),
                            rs.getString("genre"),
                            rs.getInt("artist_id")
                    );
                }
            }
        }
        return null;
    }

    public List<Album> readAll() throws SQLException {
        List<Album> list = new ArrayList<>();
        String sql = "SELECT * FROM Album";
        try (Connection conn = DBUtil.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql);
             ResultSet rs = stmt.executeQuery()) {
            while (rs.next()) {
                Date d = rs.getDate("release_date");
                list.add(new Album(
                        rs.getInt("album_id"),
                        rs.getString("title"),
                        d != null ? d.toString() : null,
                        rs.getInt("song_count"),
                        rs.getString("genre"),
                        rs.getInt("artist_id")
                ));
            }
        }
        return list;
    }
}