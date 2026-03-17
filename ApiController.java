package musicdb1.music_service;

import org.springframework.web.bind.annotation.*;
import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/api")
public class ApiController {

    private final BusinessManager bm = new BusinessManager();

    // ========= ARTISTS =========

    // GET all artists
    @GetMapping("/artists")
    public List<Artist> getAllArtists() throws Exception {
        return bm.getAllArtists();
    }

    // GET single artist
    @GetMapping("/artists/{id}")
    public Artist getArtistById(@PathVariable int id) throws Exception {
        return bm.getArtistById(id);
    }

    // CREATE artist (POST)
    @PostMapping("/artists")
    public Artist createArtist(@RequestBody Artist artist) throws Exception {
        // ensure new entity: set id to 0 so BusinessManager creates it
        artist.setArtistId(0);
        return bm.saveArtist(artist);
    }

    // UPDATE artist (PUT)
    @PutMapping("/artists/{id}")
    public Artist updateArtist(@PathVariable int id, @RequestBody Artist artist) throws Exception {
        artist.setArtistId(id);
        return bm.saveArtist(artist);
    }


    // ========= ALBUMS =========

    // GET all albums or albums by artist
    @GetMapping("/albums")
    public List<Album> getAllAlbums(
            @RequestParam(value = "artistId", required = false) Integer artistId)
            throws Exception {

        List<Album> all = bm.getAllAlbums();
        if (artistId == null) return all;

        return all.stream()
                .filter(a -> a.getArtistId() == artistId)
                .collect(Collectors.toList());
    }

    // GET single album
    @GetMapping("/albums/{id}")
    public Album getAlbumById(@PathVariable int id) throws Exception {
        return bm.getAlbumById(id);
    }

    // CREATE album (POST)
    @PostMapping("/albums")
    public Album createAlbum(@RequestBody Album album) throws Exception {
        album.setAlbumId(0);
        return bm.saveAlbum(album);
    }

    // UPDATE album (PUT)
    @PutMapping("/albums/{id}")
    public Album updateAlbum(@PathVariable int id, @RequestBody Album album) throws Exception {
        album.setAlbumId(id);
        return bm.saveAlbum(album);
    }


    // ========= SONGS =========

    // GET all songs or songs by album
    @GetMapping("/songs")
    public List<Song> getAllSongs(
            @RequestParam(value = "albumId", required = false) Integer albumId)
            throws Exception {

        List<Song> all = bm.getAllSongs();
        if (albumId == null) return all;

        return all.stream()
                .filter(s -> s.getAlbumId() == albumId)
                .collect(Collectors.toList());
    }

    // GET single song
    @GetMapping("/songs/{id}")
    public Song getSongById(@PathVariable int id) throws Exception {
        return bm.getSongById(id);
    }

    // CREATE song (POST)
    @PostMapping("/songs")
    public Song createSong(@RequestBody Song song) throws Exception {
        song.setSongId(0);
        return bm.saveSong(song);
    }

    // UPDATE song (PUT)
    @PutMapping("/songs/{id}")
    public Song updateSong(@PathVariable int id, @RequestBody Song song) throws Exception {
        song.setSongId(id);
        return bm.saveSong(song);
    }

}
