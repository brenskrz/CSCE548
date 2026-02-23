package musicdb1.music_service;

/**
 * POJO for Song — includes public setSongId(int) setter required by SongDAO.
 */
public class Song {
    private int songId;
    private String title;
    private int durationSeconds;
    private String releaseDate; // "YYYY-MM-DD"
    private String genre;
    private int albumId;

    public Song() {}

    public Song(int songId, String title, int durationSeconds, String releaseDate, String genre, int albumId) {
        this.songId = songId;
        this.title = title;
        this.durationSeconds = durationSeconds;
        this.releaseDate = releaseDate;
        this.genre = genre;
        this.albumId = albumId;
    }

    public int getSongId() {
        return songId;
    }

    // THIS is the exact setter the DAO expects:
    public void setSongId(int songId) {
        this.songId = songId;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public int getDurationSeconds() {
        return durationSeconds;
    }

    public void setDurationSeconds(int durationSeconds) {
        this.durationSeconds = durationSeconds;
    }

    public String getReleaseDate() {
        return releaseDate;
    }

    public void setReleaseDate(String releaseDate) {
        this.releaseDate = releaseDate;
    }

    public String getGenre() {
        return genre;
    }

    public void setGenre(String genre) {
        this.genre = genre;
    }

    public int getAlbumId() {
        return albumId;
    }

    public void setAlbumId(int albumId) {
        this.albumId = albumId;
    }

    @Override
    public String toString() {
        return "Song{" +
                "songId=" + songId +
                ", title='" + title + '\'' +
                ", durationSeconds=" + durationSeconds +
                ", releaseDate='" + releaseDate + '\'' +
                ", genre='" + genre + '\'' +
                ", albumId=" + albumId +
                '}';
    }
}