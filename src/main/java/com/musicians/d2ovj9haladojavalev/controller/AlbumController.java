package com.musicians.d2ovj9haladojavalev.controller;

import com.musicians.d2ovj9haladojavalev.entity.Album;
import com.musicians.d2ovj9haladojavalev.service.AlbumService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;

@RestController
@RequestMapping("/api")
public class AlbumController {
    private final AlbumService albumService;

    @Autowired
    public AlbumController(AlbumService albumService) {
        this.albumService = albumService;
    }

    @GetMapping("/album")
    public ArrayList<Album> getAllAlbum() {
        return albumService.getAllAlbum();
    }

    @GetMapping("/album/{id}")
    public Album getAlbumById(@PathVariable Long id) {
        return albumService.getAlbumById(id);
    }

    @PostMapping("/album")
    public Album addAlbum(@Valid @RequestBody Album album,
                          BindingResult bindingResult) {
        if (bindingResult.hasErrors()) {
            System.out.println(bindingResult.getAllErrors());
        }
        albumService.addAlbum(album);
        return album;
    }

    @DeleteMapping("/album/{id}")
    public void deleteAlbumById(@PathVariable Long id) {
        albumService.deleteAlbumById(id);
    }
}
