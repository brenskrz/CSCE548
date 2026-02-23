package musicdb1.music_service;

public class Album {
    private int albumId;
    private String title;
    private String releaseDate; // "YYYY-MM-DD"
    private int songCount;
    private String genre;
    private int artistId;

    public Album() {}

    public Album(int albumId, String title, String releaseDate, int songCount, String genre, int artistId) {
        this.albumId = albumId;
        this.title = title;
        this.releaseDate = releaseDate;
        this.songCount = songCount;
        this.genre = genre;
        this.artistId = artistId;
    }

    public int getAlbumId() { return albumId; }
    public void setAlbumId(int albumId) { this.albumId = albumId; }

    public String getTitle() { return title; }
    public void setTitle(String title) { this.title = title; }

    public String getReleaseDate() { return releaseDate; }
    public void setReleaseDate(String releaseDate) { this.releaseDate = releaseDate; }

    public int getSongCount() { return songCount; }
    public void setSongCount(int songCount) { this.songCount = songCount; }

    public String getGenre() { return genre; }
    public void setGenre(String genre) { this.genre = genre; }

    public int getArtistId() { return artistId; }
    public void setArtistId(int artistId) { this.artistId = artistId; }

    @Override
    public String toString() {
        return "Album{" +
                "albumId=" + albumId +
                ", title='" + title + '\'' +
                ", releaseDate='" + releaseDate + '\'' +
                ", songCount=" + songCount +
                ", genre='" + genre + '\'' +
                ", artistId=" + artistId +
                '}';
    }
}