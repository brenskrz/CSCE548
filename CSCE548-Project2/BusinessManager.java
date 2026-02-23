package musicdb1.music_service;

import java.util.List;

public class BusinessManager {

    private ArtistDAO artistDAO = new ArtistDAO();
    private AlbumDAO albumDAO = new AlbumDAO();
    private SongDAO songDAO = new SongDAO();

    // ARTIST
    public Artist saveArtist(Artist artist) throws Exception {
        if (artist.getArtistId() == 0) {
            artist = artistDAO.create(artist);
        } else {
            artistDAO.update(artist);
        }
        return artist;
    }
    public void deleteArtist(int id) throws Exception { artistDAO.delete(id); }
    public Artist getArtistById(int id) throws Exception { return artistDAO.readById(id); }
    public List<Artist> getAllArtists() throws Exception { return artistDAO.readAll(); }

    // ALBUM
    public Album saveAlbum(Album album) throws Exception {
        if (album.getAlbumId() == 0) {
            album = albumDAO.create(album);
        } else {
            albumDAO.update(album);
        }
        return album;
    }
    public void deleteAlbum(int id) throws Exception { albumDAO.delete(id); }
    public Album getAlbumById(int id) throws Exception { return albumDAO.readById(id); }
    public List<Album> getAllAlbums() throws Exception { return albumDAO.readAll(); }

    // SONG
    public Song saveSong(Song song) throws Exception {
        if (song.getSongId() == 0) {
            song = songDAO.create(song);
        } else {
            songDAO.update(song);
        }
        return song;
    }
    public void deleteSong(int id) throws Exception { songDAO.delete(id); }
    public Song getSongById(int id) throws Exception { return songDAO.readById(id); }
    public List<Song> getAllSongs() throws Exception { return songDAO.readAll(); }
}