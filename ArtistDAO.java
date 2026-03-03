package musicdb1.music_service;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class ArtistDAO {

    public Artist create(Artist artist) throws SQLException {
        String sql = "INSERT INTO Artist (name, genre, album_count, country) VALUES (?, ?, ?, ?)";

        try (Connection conn = DBUtil.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS)) {

            stmt.setString(1, artist.getName());
            stmt.setString(2, artist.getGenre());
            stmt.setInt(3, artist.getAlbumCount());
            stmt.setString(4, artist.getCountry());

            int affected = stmt.executeUpdate();
            if (affected == 0) throw new SQLException("Creating artist failed, no rows affected.");

            try (ResultSet keys = stmt.getGeneratedKeys()) {
                if (keys.next()) {
                    artist.setArtistId(keys.getInt(1));
                    return artist;
                } else {
                    throw new SQLException("Creating artist failed, no ID obtained.");
                }
            }
        }
    }

    public void update(Artist artist) throws SQLException {
        String sql = "UPDATE Artist SET name=?, genre=?, album_count=?, country=? WHERE artist_id=?";

        try (Connection conn = DBUtil.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql)) {

            stmt.setString(1, artist.getName());
            stmt.setString(2, artist.getGenre());
            stmt.setInt(3, artist.getAlbumCount());
            stmt.setString(4, artist.getCountry());
            stmt.setInt(5, artist.getArtistId());

            stmt.executeUpdate();
        }
    }

    public void delete(int id) throws SQLException {
        String sql = "DELETE FROM Artist WHERE artist_id=?";
        try (Connection conn = DBUtil.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql)) {
            stmt.setInt(1, id);
            stmt.executeUpdate();
        }
    }

    public Artist readById(int id) throws SQLException {
        String sql = "SELECT * FROM Artist WHERE artist_id=?";
        try (Connection conn = DBUtil.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql)) {
            stmt.setInt(1, id);
            try (ResultSet rs = stmt.executeQuery()) {
                if (rs.next()) {
                    return new Artist(
                            rs.getInt("artist_id"),
                            rs.getString("name"),
                            rs.getString("genre"),
                            rs.getInt("album_count"),
                            rs.getString("country")
                    );
                }
            }
        }
        return null;
    }

    public List<Artist> readAll() throws SQLException {
        List<Artist> list = new ArrayList<>();
        String sql = "SELECT * FROM Artist";
        try (Connection conn = DBUtil.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql);
             ResultSet rs = stmt.executeQuery()) {
            while (rs.next()) {
                list.add(new Artist(
                        rs.getInt("artist_id"),
                        rs.getString("name"),
                        rs.getString("genre"),
                        rs.getInt("album_count"),
                        rs.getString("country")
                ));
            }
        }
        return list;
    }
}