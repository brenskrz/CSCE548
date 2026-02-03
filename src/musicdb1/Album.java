package musicdb1;

public class Album {

    private int albumId;
    private String title;
    private String releaseDate;
    private int songCount;
    private String genre;
    private int artistId;

    public Album(int albumId, String title, String releaseDate, int songCount, String genre, int artistId) {
        this.albumId = albumId;
        this.title = title;
        this.releaseDate = releaseDate;
        this.songCount = songCount;
        this.genre = genre;
        this.artistId = artistId;
    }

    public int getAlbumId() {
        return albumId;
    }

    public String getTitle() {
        return title;
    }

    public String getReleaseDate() {
        return releaseDate;
    }

    public int getSongCount() {
        return songCount;
    }

    public String getGenre() {
        return genre;
    }

    public int getArtistId() {
        return artistId;
    }

	public void setTitle(String string) {
		// TODO Auto-generated method stub
		
	}

	public void setSongCount(int i) {
		// TODO Auto-generated method stub
		
	}
}