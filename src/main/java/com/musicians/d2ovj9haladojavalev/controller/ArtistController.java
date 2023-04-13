package com.musicians.d2ovj9haladojavalev.controller;

import com.musicians.d2ovj9haladojavalev.dto.ArtistDTO;
import com.musicians.d2ovj9haladojavalev.entity.Album;
import com.musicians.d2ovj9haladojavalev.entity.Artist;
import com.musicians.d2ovj9haladojavalev.entity.Musician;
import com.musicians.d2ovj9haladojavalev.exception.DataNotFoundException;
import com.musicians.d2ovj9haladojavalev.service.AlbumService;
import com.musicians.d2ovj9haladojavalev.service.ArtistService;
import com.musicians.d2ovj9haladojavalev.service.MusicianService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;

@RestController
@RequestMapping("/api")
public class ArtistController {

    private final ArtistService artistService;
    private final MusicianService musicianService;
    private final AlbumService albumService;

    @Autowired
    public ArtistController(ArtistService theArtistService,
                            MusicianService musicianService, AlbumService albumService) {
        artistService = theArtistService;
        this.musicianService = musicianService;
        this.albumService = albumService;
    }

    @GetMapping("/artist/{id}")
    public Artist getArtist(@PathVariable Long id) {
        Artist artist = artistService.getArtist(id);
        if (artist == null) {
            throw new DataNotFoundException("...Whoops! No data found.");
        }
        return artist;
    }

    // ehelyett DTO
    @GetMapping("/artist")
    public List<Artist> getAllArtist() {
        List<Artist> artistList = artistService.getAllArtist();
        if (artistList.isEmpty()) {
            throw new DataNotFoundException("...Whoops! No data found.");
        }
        return artistList;
    }

    // ez lesz a dto-s impl
    @GetMapping("/artistdto")
    public List<ArtistDTO> getAllArtistDto() {
        List<ArtistDTO> artistList = artistService.getAllArtistDto();
        if (artistList.isEmpty()) {
            throw new DataNotFoundException("...Whoops! No data found.");
        }
        return artistList;
    }

    @GetMapping("/artist/genre/{genre}")
    public ArrayList<Artist> getAllArtistByGenre(@PathVariable String genre) {
        ArrayList<Artist> artists = artistService.getAllArtistByGenre(genre);
        if (artists.isEmpty()) {
            throw new DataNotFoundException("...Whoops! No data found.");
        }
        return artists;
    }

    @GetMapping("artist/{year}")
    public ArrayList<Artist> getAllArtistByYear(@PathVariable int year) {
        ArrayList<Artist> artists = artistService.getAllArtistByYear(year);
        if (artists.isEmpty()) {
            throw new DataNotFoundException("...Whoops! No data found.");
        }
        return artists;
    }

    @PostMapping("/artist")
    public Artist addArtist(@Valid @RequestBody Artist theArtist, BindingResult bindingResult) {
        if (bindingResult.hasErrors()) {
            System.out.println(bindingResult.getAllErrors());
        }
        artistService.addArtist(theArtist);
        return theArtist;
    }

    @PostMapping("/artist/addMultiple")
    public List<Artist> addMultipleArtists(@Valid @RequestBody List<Artist> artists,
                                           BindingResult bindingResult) {
        if (bindingResult.hasErrors()) {
            System.out.println(bindingResult.getAllErrors());
        }
        artistService.addMultipleArtists(artists);
        return artists;
    }

    @PutMapping("/artist/{id}")
    public Artist updateArtist(@PathVariable Long id, @Valid @RequestBody Artist theArtist, BindingResult bindingResult) {
        if (bindingResult.hasErrors()) {
            System.out.println(bindingResult.getAllErrors());
        }
        theArtist.setId(id);
        artistService.addArtist(theArtist);
        return theArtist;
    }

    @PutMapping("/artist/{artistId}/album/{albumId}")
    public Artist setArtistMusicianRelationship(
            @PathVariable Long artistId,
            @PathVariable Long albumId
    ) {
        Artist artist = artistService.getArtist(artistId);
        Album album = albumService.getAlbumById(albumId);
        if (artist == null || album == null) {
            throw new DataNotFoundException("...Whoops! No data found.");
        }
        artist.addAlbum(album);
        album.setArtist(artist);
        artistService.addArtist(artist);
        return artist;
    }

    @DeleteMapping("/artist/{id}")
    public String deleteArtist(@PathVariable Long id) {
        Artist artist = artistService.getArtist(id);
        if (artist == null) {
            throw new DataNotFoundException("...Whoops! Artist with id " + id + " not found.");
        } else {
            artistService.deleteArtist(id);
        }
        return "Artist deleted with id: " + id;
    }


}
