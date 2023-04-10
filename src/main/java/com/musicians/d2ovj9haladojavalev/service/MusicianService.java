package com.musicians.d2ovj9haladojavalev.service;

import com.musicians.d2ovj9haladojavalev.entity.Musician;
import com.musicians.d2ovj9haladojavalev.persist.MusicianDAO;
import org.springframework.stereotype.Service;

import java.util.ArrayList;

@Service
public class MusicianService {

    private final MusicianDAO musicianDAO;

    public MusicianService(MusicianDAO musicianDAO) {
        this.musicianDAO = musicianDAO;
    }
    public ArrayList<Musician> getAllMusicians() {
        return (ArrayList<Musician>) musicianDAO.findAll();
    }

    public Musician getMusicianById(Long id) {
        return musicianDAO.findById(id).get();
    }

    public Musician getMusicianByInstrument(String instrument) {
        return musicianDAO.findMusicianByInstrument(instrument);
    }

    public void addMusician(Musician musician) {
        musicianDAO.save(musician);
    }

    public void deleteMusicianById(Long id) {
        musicianDAO.deleteById(id);
    }
}
