package com.musicians.d2ovj9haladojavalev.controller;

import com.musicians.d2ovj9haladojavalev.dto.MusicianDTO;
import com.musicians.d2ovj9haladojavalev.entity.Musician;
import com.musicians.d2ovj9haladojavalev.service.MusicianService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;

@RestController
@RequestMapping("/api")
public class MusicianController {

    private final MusicianService musicianService;

    @Autowired
    public MusicianController(MusicianService musicianService) {
        this.musicianService = musicianService;
    }

    @GetMapping("/musician")
    public ArrayList<Musician> getAllMusicians() {
        return musicianService.getAllMusicians();
    }

    @GetMapping("/musiciandto")
    public List<MusicianDTO> getAllMusiciansDto() {
        return musicianService.getAllMusiciansDto();
    }

    @GetMapping("/musician/{id}")
    public Musician getMusicianById(@PathVariable Long id) {
        return musicianService.getMusicianById(id);
    }

    @GetMapping("/musician/{instrument}")
    public Musician getMusicianByInstrument(@PathVariable String instrument) {
        return musicianService.getMusicianByInstrument(instrument);
    }

    @PostMapping("/musician")
    public void addMusician(@Valid @RequestBody Musician musician, BindingResult bindingResult) {
        if (bindingResult.hasErrors()) {
            System.out.println(bindingResult.getAllErrors());
        }
        musicianService.addMusician(musician);
    }

    @PutMapping("musician/{id}")
    public Musician updateMusicianById(@PathVariable Long id, @Valid @RequestBody Musician musician,
                                       BindingResult bindingResult) {
        if (bindingResult.hasErrors()) {
            System.out.println(bindingResult.getAllErrors());
        }
        musician.setId(id);
        musicianService.addMusician(musician);
        return musician;
    }

    @DeleteMapping("/musician/{id}")
    public void deleteMusicianById(@PathVariable Long id) {
        musicianService.deleteMusicianById(id);
    }


}
