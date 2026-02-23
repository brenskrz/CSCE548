package musicdb1.music_service;

public class Artist {
    private int artistId;
    private String name;
    private String genre;
    private int albumCount;
    private String country;

    public Artist() {}

    public Artist(int artistId, String name, String genre, int albumCount, String country) {
        this.artistId = artistId;
        this.name = name;
        this.genre = genre;
        this.albumCount = albumCount;
        this.country = country;
    }

    public int getArtistId() { return artistId; }
    public void setArtistId(int artistId) { this.artistId = artistId; }

    public String getName() { return name; }
    public void setName(String name) { this.name = name; }

    public String getGenre() { return genre; }
    public void setGenre(String genre) { this.genre = genre; }

    public int getAlbumCount() { return albumCount; }
    public void setAlbumCount(int albumCount) { this.albumCount = albumCount; }

    public String getCountry() { return country; }
    public void setCountry(String country) { this.country = country; }

    @Override
    public String toString() {
        return "Artist{" +
                "artistId=" + artistId +
                ", name='" + name + '\'' +
                ", genre='" + genre + '\'' +
                ", albumCount=" + albumCount +
                ", country='" + country + '\'' +
                '}';
    }
}