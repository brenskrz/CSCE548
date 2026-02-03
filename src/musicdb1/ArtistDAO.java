package musicdb1;

import java.sql.*;

public class ArtistDAO {

    public void createArtist(Artist artist) throws SQLException {
        String sql = "INSERT INTO Artist (name, genre, album_count, country) VALUES (?, ?, ?, ?)";

        try (Connection conn = DBUtil.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql)) {

            stmt.setString(1, artist.getName());
            stmt.setString(2, artist.getGenre());
            stmt.setInt(3, artist.getAlbumCount());
            stmt.setString(4, artist.getCountry());
            stmt.executeUpdate();
        }
    }

    public Artist getArtistById(int artistId) throws SQLException {
        String sql = "SELECT * FROM Artist WHERE artist_id=?";

        try (Connection conn = DBUtil.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql)) {

            stmt.setInt(1, artistId);
            ResultSet rs = stmt.executeQuery();

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
        return null;
    }

    public void updateArtist(Artist artist) throws SQLException {
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

    public void deleteArtist(int artistId) throws SQLException {
        String sql = "DELETE FROM Artist WHERE artist_id=?";

        try (Connection conn = DBUtil.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql)) {

            stmt.setInt(1, artistId);
            stmt.executeUpdate();
        }
    }
}
