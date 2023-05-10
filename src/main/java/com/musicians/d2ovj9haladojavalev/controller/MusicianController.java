package com.musicians.d2ovj9haladojavalev.controller;

import com.musicians.d2ovj9haladojavalev.dto.MusicianDTO;
import com.musicians.d2ovj9haladojavalev.entity.Artist;
import com.musicians.d2ovj9haladojavalev.entity.Musician;
import com.musicians.d2ovj9haladojavalev.exception.DataNotFoundException;
import com.musicians.d2ovj9haladojavalev.service.ArtistService;
import com.musicians.d2ovj9haladojavalev.service.MusicianService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import java.util.*;

@RestController
@RequestMapping("/api/v1/musician")
public class MusicianController {

    private final MusicianService musicianService;
    private final ArtistService artistService;

    @Autowired
    public MusicianController(MusicianService musicianService, ArtistService artistService) {
        this.musicianService = musicianService;
        this.artistService = artistService;
    }

    @GetMapping
    public ArrayList<Musician> getAllMusicians() {
        return musicianService.getAllMusicians();
    }

    @GetMapping("/dto")
    public List<MusicianDTO> getAllMusiciansDto() {
        return musicianService.getAllMusiciansDto();
    }

    @GetMapping("/{id}")
    public Musician getMusicianById(@PathVariable Long id) {
        return musicianService.getMusicianById(id);
    }

    @GetMapping("/{instrument}")
    public Musician getMusicianByInstrument(@PathVariable String instrument) {
        return musicianService.getMusicianByInstrument(instrument);
    }

    @PostMapping
    public void addMusician(@Valid @RequestBody Musician musician, BindingResult bindingResult) {
        if (bindingResult.hasErrors()) {
            System.out.println(bindingResult.getAllErrors());
        }
        if (musician.getAssociatedActs() != null) {
            try {
                List<Long> associatedActsIds = musician.getAssociatedActs()
                        .stream()
                        .map(Artist::getId)
                        .toList();
                Set<Artist> associatedActs = new HashSet<>();
                associatedActsIds.forEach(id -> {
                    Artist artist = artistService.getArtist(id);
                    associatedActs.add(artist);
                    artist.setArtistMusicianRelationship(musician);
                    // vmi kéne h beletegye az artistba is?
                });
                musician.setAssociatedActs(associatedActs);
            } catch (NoSuchElementException e) {
                throw new DataNotFoundException("Whoops! Cannot find artist"); // with id...
            }
        }
        musicianService.addMusician(musician);
    }

    @PutMapping("/{id}")
    public Musician updateMusicianById(@PathVariable Long id, @Valid @RequestBody Musician musician,
                                       BindingResult bindingResult) {
        if (bindingResult.hasErrors()) {
            System.out.println(bindingResult.getAllErrors());
        }
        musician.setId(id);
        musicianService.addMusician(musician);
        return musician;
    }

    @DeleteMapping("/{id}")
    public void deleteMusicianById(@PathVariable Long id) {
        musicianService.deleteMusicianById(id);
    }


}
