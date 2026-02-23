package musicdb1.music_service;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class DataProvider {

    /* ========================= ARTIST ========================= */

    public int insertArtist(Artist artist) throws SQLException {
        String sql = "INSERT INTO Artist (name, genre, album_count, country) VALUES (?, ?, ?, ?)";
        try (Connection conn = DBUtil.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS)) {

            stmt.setString(1, artist.getName());
            stmt.setString(2, artist.getGenre());
            stmt.setInt(3, artist.getAlbumCount());
            stmt.setString(4, artist.getCountry());
            stmt.executeUpdate();

            ResultSet keys = stmt.getGeneratedKeys();
            if (keys.next()) {
                return keys.getInt(1);
            }
        }
        return 0;
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

    public void deleteArtist(int id) throws SQLException {
        String sql = "DELETE FROM Artist WHERE artist_id=?";
        try (Connection conn = DBUtil.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql)) {

            stmt.setInt(1, id);
            stmt.executeUpdate();
        }
    }

    public Artist readArtistById(int id) throws SQLException {
        String sql = "SELECT * FROM Artist WHERE artist_id=?";
        try (Connection conn = DBUtil.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql)) {

            stmt.setInt(1, id);
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

    public List<Artist> readAllArtists() throws SQLException {
        List<Artist> list = new ArrayList<>();
        String sql = "SELECT * FROM Artist";

        try (Connection conn = DBUtil.getConnection();
             Statement stmt = conn.createStatement();
             ResultSet rs = stmt.executeQuery(sql)) {

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

    /* ========================= ALBUM ========================= */

    public int insertAlbum(Album album) throws SQLException {
        String sql = "INSERT INTO Album (title, release_date, song_count, genre, artist_id) VALUES (?, ?, ?, ?, ?)";

        try (Connection conn = DBUtil.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS)) {

            stmt.setString(1, album.getTitle());
            stmt.setDate(2, Date.valueOf(album.getReleaseDate()));
            stmt.setInt(3, album.getSongCount());
            stmt.setString(4, album.getGenre());
            stmt.setInt(5, album.getArtistId());
            stmt.executeUpdate();

            ResultSet keys = stmt.getGeneratedKeys();
            if (keys.next()) {
                return keys.getInt(1);
            }
        }
        return 0;
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

    public void deleteAlbum(int id) throws SQLException {
        String sql = "DELETE FROM Album WHERE album_id=?";
        try (Connection conn = DBUtil.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql)) {

            stmt.setInt(1, id);
            stmt.executeUpdate();
        }
    }

    public Album readAlbumById(int id) throws SQLException {
        String sql = "SELECT * FROM Album WHERE album_id=?";
        try (Connection conn = DBUtil.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql)) {

            stmt.setInt(1, id);
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

    public List<Album> readAllAlbums() throws SQLException {
        List<Album> list = new ArrayList<>();
        String sql = "SELECT * FROM Album";

        try (Connection conn = DBUtil.getConnection();
             Statement stmt = conn.createStatement();
             ResultSet rs = stmt.executeQuery(sql)) {

            while (rs.next()) {
                list.add(new Album(
                        rs.getInt("album_id"),
                        rs.getString("title"),
                        rs.getDate("release_date").toString(),
                        rs.getInt("song_count"),
                        rs.getString("genre"),
                        rs.getInt("artist_id")
                ));
            }
        }
        return list;
    }

    /* ========================= SONG ========================= */

    public int insertSong(Song song) throws SQLException {
        String sql = "INSERT INTO Song (title, duration_seconds, release_date, genre, album_id) VALUES (?, ?, ?, ?, ?)";

        try (Connection conn = DBUtil.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS)) {

            stmt.setString(1, song.getTitle());
            stmt.setInt(2, song.getDurationSeconds());
            stmt.setDate(3, Date.valueOf(song.getReleaseDate()));
            stmt.setString(4, song.getGenre());
            stmt.setInt(5, song.getAlbumId());
            stmt.executeUpdate();

            ResultSet keys = stmt.getGeneratedKeys();
            if (keys.next()) {
                return keys.getInt(1);
            }
        }
        return 0;
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

    public void deleteSong(int id) throws SQLException {
        String sql = "DELETE FROM Song WHERE song_id=?";
        try (Connection conn = DBUtil.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql)) {

            stmt.setInt(1, id);
            stmt.executeUpdate();
        }
    }

    public Song readSongById(int id) throws SQLException {
        String sql = "SELECT * FROM Song WHERE song_id=?";
        try (Connection conn = DBUtil.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql)) {

            stmt.setInt(1, id);
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

    public List<Song> readAllSongs() throws SQLException {
        List<Song> list = new ArrayList<>();
        String sql = "SELECT * FROM Song";

        try (Connection conn = DBUtil.getConnection();
             Statement stmt = conn.createStatement();
             ResultSet rs = stmt.executeQuery(sql)) {

            while (rs.next()) {
                list.add(new Song(
                        rs.getInt("song_id"),
                        rs.getString("title"),
                        rs.getInt("duration_seconds"),
                        rs.getDate("release_date").toString(),
                        rs.getString("genre"),
                        rs.getInt("album_id")
                ));
            }
        }
        return list;
    }
}