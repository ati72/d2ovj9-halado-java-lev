package com.musicians.d2ovj9haladojavalev.controller;

import com.musicians.d2ovj9haladojavalev.dto.AlbumDTO;
import com.musicians.d2ovj9haladojavalev.entity.Album;
import com.musicians.d2ovj9haladojavalev.entity.Artist;
import com.musicians.d2ovj9haladojavalev.exception.DataNotFoundException;
import com.musicians.d2ovj9haladojavalev.service.AlbumService;
import com.musicians.d2ovj9haladojavalev.service.ArtistService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;
import java.util.NoSuchElementException;

@RestController
@RequestMapping("/api/v1/album")
public class AlbumController {
    private final AlbumService albumService;
    private final ArtistService artistService;

    @Autowired
    public AlbumController(AlbumService albumService, ArtistService artistService) {
        this.albumService = albumService;
        this.artistService = artistService;
    }

    @GetMapping
    public ArrayList<Album> getAllAlbum() {
        return albumService.getAllAlbum();
    }

    @GetMapping("/dto")
    public List<AlbumDTO> getAllAlbumDto() {
        return albumService.getAllAlbumDto();
    }

    @GetMapping("/{id}")
    public Album getAlbumById(@PathVariable Long id) {
        return albumService.getAlbumById(id);
    }

    @PostMapping
    public Album addAlbum(@Valid @RequestBody Album album,
                          BindingResult bindingResult) {
        if (bindingResult.hasErrors()) {
            System.out.println(bindingResult.getAllErrors());
        }
        if (album.getArtist() != null) {
            Artist artist = artistService.getArtist(album.getArtist().getId());
            album.setArtist(artist);
        }
        albumService.addAlbum(album);
        return album;
    }

    @DeleteMapping("/{id}")
    public String deleteAlbumById(@PathVariable Long id) {
        Album album = albumService.getAlbumById(id);
        albumService.deleteAlbumById(id);

        return "Artist deleted with id: " + id;
    }
}
