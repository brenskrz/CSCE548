package musicdb1;

public class Artist {

    private int artistId;
    private String name;
    private String genre;
    private int albumCount;
    private String country;

    public Artist(int artistId, String name, String genre, int albumCount, String country) {
        this.artistId = artistId;
        this.name = name;
        this.genre = genre;
        this.albumCount = albumCount;
        this.country = country;
    }

    public int getArtistId() {
        return artistId;
    }

    public String getName() {
        return name;
    }

    public String getGenre() {
        return genre;
    }

    public int getAlbumCount() {
        return albumCount;
    }

    public String getCountry() {
        return country;
    }

	public void setCountry(String string) {
		// TODO Auto-generated method stub
		
	}

	public void setAlbumCount(int i) {
		// TODO Auto-generated method stub
		
	}
}