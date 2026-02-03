package musicdb1;

import java.sql.*;

public class SongDAO {

    public void createSong(Song song) throws SQLException {
        String sql = "INSERT INTO Song (title, duration_seconds, release_date, genre, album_id) VALUES (?, ?, ?, ?, ?)";

        try (Connection conn = DBUtil.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql)) {

            stmt.setString(1, song.getTitle());
            stmt.setInt(2, song.getDurationSeconds());
            stmt.setDate(3, Date.valueOf(song.getReleaseDate()));
            stmt.setString(4, song.getGenre());
            stmt.setInt(5, song.getAlbumId());
            stmt.executeUpdate();
        }
    }

    public Song getSongById(int songId) throws SQLException {
        String sql = "SELECT * FROM Song WHERE song_id=?";

        try (Connection conn = DBUtil.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql)) {

            stmt.setInt(1, songId);
            ResultSet rs = stmt.executeQuery();

            if (rs.next()) {
                return new Song(
                        rs.getInt("song_id"),
                        rs.getString("title"),
                        rs.getInt("duration_seconds"),
                        rs.getDate("release_date").toString(),
                        rs.getString("genre"),
                        rs.getInt("album_id")
                );
            }
        }
        return null;
    }

    public void updateSong(Song song) throws SQLException {
        String sql = "UPDATE Song SET title=?, duration_seconds=?, release_date=?, genre=?, album_id=? WHERE song_id=?";

        try (Connection conn = DBUtil.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql)) {

            stmt.setString(1, song.getTitle());
            stmt.setInt(2, song.getDurationSeconds());
            stmt.setDate(3, Date.valueOf(song.getReleaseDate()));
            stmt.setString(4, song.getGenre());
            stmt.setInt(5, song.getAlbumId());
            stmt.setInt(6, song.getSongId());
            stmt.executeUpdate();
        }
    }

    public void deleteSong(int songId) throws SQLException {
        String sql = "DELETE FROM Song WHERE song_id=?";

        try (Connection conn = DBUtil.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql)) {

            stmt.setInt(1, songId);
            stmt.executeUpdate();
        }
    }
}
