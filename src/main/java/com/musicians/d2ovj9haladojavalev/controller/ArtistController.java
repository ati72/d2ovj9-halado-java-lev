package com.musicians.d2ovj9haladojavalev.controller;

import com.musicians.d2ovj9haladojavalev.dto.ArtistDTO;
import com.musicians.d2ovj9haladojavalev.entity.Album;
import com.musicians.d2ovj9haladojavalev.entity.Artist;
import com.musicians.d2ovj9haladojavalev.entity.Publisher;
import com.musicians.d2ovj9haladojavalev.service.AlbumService;
import com.musicians.d2ovj9haladojavalev.service.ArtistService;
import com.musicians.d2ovj9haladojavalev.service.PublisherService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;

@RestController
@RequestMapping("/api/v1/artist")
public class ArtistController {

    private final ArtistService artistService;
    private final AlbumService albumService;
    private final PublisherService publisherService;

    @Autowired
    public ArtistController(ArtistService theArtistService,
                            AlbumService albumService,
                            PublisherService publisherService) {
        artistService = theArtistService;
        this.albumService = albumService;
        this.publisherService = publisherService;
    }

    @GetMapping("/{id}")
    public Artist getArtist(@PathVariable Long id) {
        return artistService.getArtist(id);
    }

    // ehelyett DTO
    @GetMapping
    public List<Artist> getAllArtist() {
        return artistService.getAllArtist();
    }

    // ez lesz a dto-s impl
    @GetMapping("/dto")
    public List<ArtistDTO> getAllArtistDto() {
        return artistService.getAllArtistDto();
    }

    @GetMapping("/genre/{genre}")
    public ArrayList<Artist> getAllArtistByGenre(@PathVariable String genre) {
        return artistService.getAllArtistByGenre(genre);
    }

    @GetMapping("/year/{year}")
    public ArrayList<Artist> getAllArtistByYear(@PathVariable int year) {
        return artistService.getAllArtistByYear(year);
    }

    @PostMapping
    public Artist addArtist(@Valid @RequestBody Artist theArtist, BindingResult bindingResult) {
        if (bindingResult.hasErrors()) {
            System.out.println(bindingResult.getAllErrors());
        }
        if (theArtist.getPublisher() != null) {
            Publisher publisher = publisherService.getPublisherById(theArtist.getPublisher().getId());
            theArtist.setPublisher(publisher);
        }
        artistService.addArtist(theArtist);
        return theArtist;
    }

    @PostMapping("/addMultiple")
    public List<Artist> addMultipleArtists(@Valid @RequestBody List<Artist> artists,
                                           BindingResult bindingResult) {
        if (bindingResult.hasErrors()) {
            System.out.println(bindingResult.getAllErrors());
        }
        artistService.addMultipleArtists(artists);
        return artists;
    }

    @PutMapping("/{id}")
    public Artist updateArtist(@PathVariable Long id, @Valid @RequestBody Artist theArtist,
                               BindingResult bindingResult) {
        if (bindingResult.hasErrors()) {
            System.out.println(bindingResult.getAllErrors());
        }
        theArtist.setId(id);
        artistService.addArtist(theArtist);
        return theArtist;
    }

    @PutMapping("/{artistId}/album/{albumId}")
    public Artist setArtistMusicianRelationship(
            @PathVariable Long artistId,
            @PathVariable Long albumId
    ) {
        Artist artist = artistService.getArtist(artistId);
        Album album = albumService.getAlbumById(albumId);
        artist.addAlbum(album);
        album.setArtist(artist);
        artistService.addArtist(artist);
        return artist;
    }

    // TODO: JdbcSQLIntegrityConstraintViolationException
    @DeleteMapping("/{id}")
    public String deleteArtist(@PathVariable Long id) {
        Artist artist = artistService.getArtist(id);
        // referencia törlése albumból
        if (!artist.getAlbums().isEmpty()) {
            artist.getAlbums()
                    .forEach(album -> album.setArtist(null));
        }
        // referencia törlés publisherből
        if (artist.getPublisher() != null) {
            artist.getPublisher().deleteArtist(artist);
        }
        // referencia törlés musicianból
        // még nem teszteltem!
        if (!artist.getMembers().isEmpty()) {
            artist.getMembers()
                    .forEach(musician -> musician.deleteArtist(artist));
        }
        artistService.deleteArtist(id);

        return "Artist deleted with id: " + id;
    }


}
