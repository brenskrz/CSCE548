package musicdb1;

import java.sql.*;

public class AlbumDAO {

    public void createAlbum(Album album) throws SQLException {
        String sql = "INSERT INTO Album (title, release_date, song_count, genre, artist_id) VALUES (?, ?, ?, ?, ?)";

        try (Connection conn = DBUtil.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql)) {

            stmt.setString(1, album.getTitle());
            stmt.setDate(2, Date.valueOf(album.getReleaseDate()));
            stmt.setInt(3, album.getSongCount());
            stmt.setString(4, album.getGenre());
            stmt.setInt(5, album.getArtistId());
            stmt.executeUpdate();
        }
    }

    public Album getAlbumById(int albumId) throws SQLException {
        String sql = "SELECT * FROM Album WHERE album_id=?";

        try (Connection conn = DBUtil.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql)) {

            stmt.setInt(1, albumId);
            ResultSet rs = stmt.executeQuery();

            if (rs.next()) {
                return new Album(
                        rs.getInt("album_id"),
                        rs.getString("title"),
                        rs.getDate("release_date").toString(),
                        rs.getInt("song_count"),
                        rs.getString("genre"),
                        rs.getInt("artist_id")
                );
            }
        }
        return null;
    }

    public void updateAlbum(Album album) throws SQLException {
        String sql = "UPDATE Album SET title=?, release_date=?, song_count=?, genre=?, artist_id=? WHERE album_id=?";

        try (Connection conn = DBUtil.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql)) {

            stmt.setString(1, album.getTitle());
            stmt.setDate(2, Date.valueOf(album.getReleaseDate()));
            stmt.setInt(3, album.getSongCount());
            stmt.setString(4, album.getGenre());
            stmt.setInt(5, album.getArtistId());
            stmt.setInt(6, album.getAlbumId());
            stmt.executeUpdate();
        }
    }

    public void deleteAlbum(int albumId) throws SQLException {
        String sql = "DELETE FROM Album WHERE album_id=?";

        try (Connection conn = DBUtil.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql)) {

            stmt.setInt(1, albumId);
            stmt.executeUpdate();
        }
    }
}
