package com.musicians.d2ovj9haladojavalev.controller;

import com.musicians.d2ovj9haladojavalev.entity.Artist;
import com.musicians.d2ovj9haladojavalev.service.ArtistService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;

@RestController
@RequestMapping("/api")
public class ArtistController {

    private final ArtistService artistService;

    @Autowired
    public ArtistController(ArtistService theArtistService) {
        artistService = theArtistService;
    }

    @GetMapping("/getArtist/{id}")
    public Artist getArtist(@PathVariable Long id) {
        return artistService.getArtist(id);
    }

    @GetMapping("/getAllArtist")
    public ArrayList<Artist> getAllArtist() {
        return artistService.getAllArtist();
    }

    @GetMapping("/getAllArtist/{genre}")
    public ArrayList<Artist> getAllArtistByGenre(@PathVariable String genre) {
        return artistService.getAllArtistByGenre(genre);
    }

    @GetMapping("getAllArtist/{year}")
    public ArrayList<Artist> getAllArtistByYear(@PathVariable int year) {
        return artistService.getAllArtistByYear(year);
    }

    @PostMapping("/addArtist")
    public Artist addArtist(@Valid @RequestBody Artist theArtist, BindingResult bindingResult) {
        if (bindingResult.hasErrors()) {
            System.out.println(bindingResult.getAllErrors());
        }
        artistService.addArtist(theArtist);
        return theArtist;
    }

    @PutMapping("/updateArtist/{id}")
    public Artist updateArtist(@PathVariable Long id, @Valid @RequestBody Artist theArtist, BindingResult bindingResult) {
        if (bindingResult.hasErrors()) {
            System.out.println(bindingResult.getAllErrors());
        }
        theArtist.setId(id);
        artistService.addArtist(theArtist);
        return theArtist;
    }

    @DeleteMapping("/deleteArtist/{id}")
    public void deleteArtist(@PathVariable Long id) {
        artistService.deleteArtist(id);
    }


}
