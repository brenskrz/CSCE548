package musicdb1;

public class Song {

    private int songId;
    private String title;
    private int durationSeconds;
    private String releaseDate;
    private String genre;
    private int albumId;

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

    public String getTitle() {
        return title;
    }

    public int getDurationSeconds() {
        return durationSeconds;
    }

    public String getReleaseDate() {
        return releaseDate;
    }

    public String getGenre() {
        return genre;
    }

    public int getAlbumId() {
        return albumId;
    }

	public void setTitle(String string) {
		// TODO Auto-generated method stub
		
	}

	public void setDurationSeconds(int i) {
		// TODO Auto-generated method stub
		
	}
}