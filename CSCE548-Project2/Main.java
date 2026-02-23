package musicdb1.music_service;

import java.sql.*;
import java.time.*;

public class Main {
    public static void main(String[] args) {
        ArtistDAO artistDAO = new ArtistDAO();
        AlbumDAO albumDAO = new AlbumDAO();
        SongDAO songDAO = new SongDAO();

        try {
            System.out.println("=== INSERT TESTS ===");

            // 1) Insert an Artist (helper returns generated id)
            int artistId = insertArtistReturnId("Test Artist", "Indie", 1, "USA");
            System.out.println("Inserted Artist id = " + artistId);

            // Read the artist via DAO
            Artist artist = artistDAO.readById(artistId);
            System.out.println("Read Artist: " + artist);

            // 2) Insert an Album for that artist
            int albumId = insertAlbumReturnId("Test Album", "2023-10-01", 8, "Indie Rock", artistId);
            System.out.println("Inserted Album id = " + albumId);

            // Read the album via DAO
            Album album = albumDAO.readById(albumId);
            System.out.println("Read Album: " + album);

            // 3) Insert a Song for that album
            int songId = insertSongReturnId("Test Song", 215, "2023-10-02", "Indie Rock", albumId);
            System.out.println("Inserted Song id = " + songId);

            // Read the song via DAO
            Song song = songDAO.readById(songId);
            System.out.println("Read Song: " + song);

            System.out.println("\n=== UPDATE TESTS ===");
            // Update Artist
            artist.setCountry("Canada");
            artist.setAlbumCount(2);
            artistDAO.update(artist);
            System.out.println("Updated Artist: " + artistDAO.readById(artistId));

            // Update Album
            album.setTitle("Test Album (Updated)");
            album.setSongCount(9);
            albumDAO.update(album);
            System.out.println("Updated Album: " + albumDAO.readById(albumId));

            // Update Song
            song.setTitle("Test Song (Remix)");
            song.setDurationSeconds(230);
            songDAO.update(song);
            System.out.println("Updated Song: " + songDAO.readById(songId));

            System.out.println("\n=== DELETE TESTS ===");
            // Delete Song
            songDAO.delete(songId);
            System.out.println("Deleted Song id " + songId + ", lookup -> " + songDAO.readById(songId));

            // Delete Album
            albumDAO.delete(albumId);
            System.out.println("Deleted Album id " + albumId + ", lookup -> " + albumDAO.readById(albumId));

            // Delete Artist
            artistDAO.delete(artistId);
            System.out.println("Deleted Artist id " + artistId + ", lookup -> " + artistDAO.readById(artistId));

            System.out.println("\n=== FINAL DB STATE ===");
            System.out.println("Artists count: " + getCount("Artist"));
            System.out.println("Albums count:  " + getCount("Album"));
            System.out.println("Songs count:   " + getCount("Song"));

            System.out.println("\nDATA LAYER TEST COMPLETED SUCCESSFULLY");

        } catch (Exception e) {
            System.err.println("Error during test run:");
            e.printStackTrace();
        }
    }

    // Helper: insert artist and return generated id
    private static int insertArtistReturnId(String name, String genre, int albumCount, String country) throws SQLException {
        String sql = "INSERT INTO Artist (name, genre, album_count, country) VALUES (?, ?, ?, ?)";
        try (Connection conn = DBUtil.getConnection();
             PreparedStatement ps = conn.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS)) {
            ps.setString(1, name);
            ps.setString(2, genre);
            ps.setInt(3, albumCount);
            ps.setString(4, country);
            ps.executeUpdate();
            try (ResultSet keys = ps.getGeneratedKeys()) {
                if (keys.next()) return keys.getInt(1);
                throw new SQLException("No artist id returned.");
            }
        }
    }

    // Helper: insert album and return generated id
    private static int insertAlbumReturnId(String title, String releaseDate, int songCount, String genre, int artistId) throws SQLException {
        String sql = "INSERT INTO Album (title, release_date, song_count, genre, artist_id) VALUES (?, ?, ?, ?, ?)";
        try (Connection conn = DBUtil.getConnection();
             PreparedStatement ps = conn.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS)) {
            ps.setString(1, title);
            ps.setDate(2, Date.valueOf(releaseDate));
            ps.setInt(3, songCount);
            ps.setString(4, genre);
            ps.setInt(5, artistId);
            ps.executeUpdate();
            try (ResultSet keys = ps.getGeneratedKeys()) {
                if (keys.next()) return keys.getInt(1);
                throw new SQLException("No album id returned.");
            }
        }
    }

    // Helper: insert song and return generated id
    private static int insertSongReturnId(String title, int durationSeconds, String releaseDate, String genre, int albumId) throws SQLException {
        String sql = "INSERT INTO Song (title, duration_seconds, release_date, genre, album_id) VALUES (?, ?, ?, ?, ?)";
        try (Connection conn = DBUtil.getConnection();
             PreparedStatement ps = conn.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS)) {
            ps.setString(1, title);
            ps.setInt(2, durationSeconds);
            ps.setDate(3, Date.valueOf(releaseDate));
            ps.setString(4, genre);
            ps.setInt(5, albumId);
            ps.executeUpdate();
            try (ResultSet keys = ps.getGeneratedKeys()) {
                if (keys.next()) return keys.getInt(1);
                throw new SQLException("No song id returned.");
            }
        }
    }

    // Helper: simple count of rows in a table
    private static int getCount(String table) throws SQLException {
        String sql = "SELECT COUNT(*) FROM " + table;
        try (Connection conn = DBUtil.getConnection();
             Statement st = conn.createStatement();
             ResultSet rs = st.executeQuery(sql)) {
            rs.next();
            return rs.getInt(1);
        }
    }
}