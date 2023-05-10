package com.musicians.d2ovj9haladojavalev.service;

import com.musicians.d2ovj9haladojavalev.dto.MusicianDTO;
import com.musicians.d2ovj9haladojavalev.entity.Artist;
import com.musicians.d2ovj9haladojavalev.entity.Musician;
import com.musicians.d2ovj9haladojavalev.exception.DataNotFoundException;
import com.musicians.d2ovj9haladojavalev.persist.MusicianDAO;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class MusicianService {

    private final MusicianDAO musicianDAO;

    public MusicianService(MusicianDAO musicianDAO) {
        this.musicianDAO = musicianDAO;
    }
    public ArrayList<Musician> getAllMusicians() {
        ArrayList<Musician> musicians = (ArrayList<Musician>) musicianDAO.findAll();
        if (musicians.isEmpty()) {
            throw new DataNotFoundException(
                    "Whoops. No musicians found in database."
            );
        }
        return musicians;
    }

    public Musician getMusicianById(Long id) {
        return musicianDAO.findById(id)
                .orElseThrow(() -> new DataNotFoundException(
                        "Whoops. No musician found with id: " + id
                ));
    }

    //TODO: ezt át kéne írni úgy, hogy listát küld vissza, nem csak egyet...
    public Musician getMusicianByInstrument(String instrument) {
        return musicianDAO.findMusicianByInstrument(instrument);
    }

    public void addMusician(Musician musician) {
        musicianDAO.save(musician);
    }

    public void deleteMusicianById(Long id) {
        musicianDAO.deleteById(id);
    }

    public List<MusicianDTO> getAllMusiciansDto() {
        List<Musician> musicianList = (List<Musician>) musicianDAO.findAll();
        if (musicianList.isEmpty()) {
            throw new DataNotFoundException(
                    "Whoops. No musicians found in database."
            );
        }
        return musicianList
                .stream()
                .map(musician -> new MusicianDTO(
                        musician.getId(),
                        musician.getFirstName(),
                        musician.getLastName(),
                        musician.getYearOfBirth(),
                        musician.getInstrument(),
                        musician.getAssociatedActs()
                                .stream()
                                .map(Artist::getName)
                                .collect(Collectors.toList())
                )).collect(Collectors.toList());
    }
}
