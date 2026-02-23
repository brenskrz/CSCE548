package musicdb1.music_service;

import java.util.List;

public class AlbumLifecycleDemo {

    public static void main(String[] args) {
        BusinessManager bm = new BusinessManager();

        try {
            System.out.println("=== STEP 1: CREATE (INSERT) ===");

            // Create artist
            Artist artist = new Artist(0, "PBJ Artist", "Indie", 0, "USA");
            artist = bm.saveArtist(artist); // insert
            System.out.println("Inserted Artist: " + artist);

            // Create album (references artist)
            Album album = new Album(0, "PBJ Album", "2024-02-24", 0, "Indie Rock", artist.getArtistId());
            album = bm.saveAlbum(album);
            System.out.println("Inserted Album:  " + album);

            // Create song (references album)
            Song song = new Song(0, "PBJ Song", 180, "2024-02-24", "Indie Rock", album.getAlbumId());
            song = bm.saveSong(song);
            System.out.println("Inserted Song:   " + song);

            // Read & print by id
            System.out.println("\n-- After Inserts --");
            System.out.println("Artist: " + bm.getArtistById(artist.getArtistId()));
            System.out.println("Album : " + bm.getAlbumById(album.getAlbumId()));
            System.out.println("Song  : " + bm.getSongById(song.getSongId()));

            // print counts
            printCounts(bm);

            System.out.println("\n=== STEP 2: UPDATE ===");
            album.setTitle(album.getTitle() + " (Updated)");
            album.setSongCount(album.getSongCount() + 1);
            bm.saveAlbum(album); // update
            System.out.println("Updated Album: " + bm.getAlbumById(album.getAlbumId()));

            song.setTitle(song.getTitle() + " (Remix)");
            song.setDurationSeconds(song.getDurationSeconds() + 10);
            bm.saveSong(song);
            System.out.println("Updated Song: " + bm.getSongById(song.getSongId()));

            artist.setCountry("Canada");
            artist.setAlbumCount(artist.getAlbumCount() + 1);
            bm.saveArtist(artist);
            System.out.println("Updated Artist: " + bm.getArtistById(artist.getArtistId()));

            System.out.println("\n-- After Updates --");
            printCounts(bm);

            System.out.println("\n=== STEP 3: DELETE (child -> parent) ===");
            bm.deleteSong(song.getSongId());
            System.out.println("Deleted Song id " + song.getSongId() + " -> lookup: " + bm.getSongById(song.getSongId()));

            bm.deleteAlbum(album.getAlbumId());
            System.out.println("Deleted Album id " + album.getAlbumId() + " -> lookup: " + bm.getAlbumById(album.getAlbumId()));

            bm.deleteArtist(artist.getArtistId());
            System.out.println("Deleted Artist id " + artist.getArtistId() + " -> lookup: " + bm.getArtistById(artist.getArtistId()));

            System.out.println("\n-- Final counts --");
            printCounts(bm);

            System.out.println("\nPBJ-STYLE DEMO COMPLETED");

        } catch (Exception e) {
            System.err.println("Error during demo:");
            e.printStackTrace();
        }
    }

    private static void printCounts(BusinessManager bm) throws Exception {
        List<Artist> a = bm.getAllArtists();
        List<Album> al = bm.getAllAlbums();
        List<Song> s = bm.getAllSongs();
        System.out.println("Counts -> Artists: " + a.size() + ", Albums: " + al.size() + ", Songs: " + s.size());
    }
}