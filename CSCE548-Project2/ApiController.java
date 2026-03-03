package musicdb1.music_service;

import org.springframework.web.bind.annotation.*;
import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/api")
public class ApiController {

    private final BusinessManager bm = new BusinessManager();

    // ========= ARTISTS =========
    @GetMapping("/artists")
    public List<Artist> getAllArtists() throws Exception {
        return bm.getAllArtists();
    }

    @GetMapping("/artists/{id}")
    public Artist getArtistById(@PathVariable int id) throws Exception {
        return bm.getArtistById(id);
    }

    // ========= ALBUMS =========
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

    @GetMapping("/albums/{id}")
    public Album getAlbumById(@PathVariable int id) throws Exception {
        return bm.getAlbumById(id);
    }

    // ========= SONGS =========
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

    @GetMapping("/songs/{id}")
    public Song getSongById(@PathVariable int id) throws Exception {
        return bm.getSongById(id);
    }
}