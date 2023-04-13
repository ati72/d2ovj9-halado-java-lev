package com.musicians.d2ovj9haladojavalev.service;


import com.musicians.d2ovj9haladojavalev.entity.Artist;
import com.musicians.d2ovj9haladojavalev.persist.ArtistDAO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class ArtistService {
    private ArtistDAO artistDAO;

    @Autowired
    public ArtistService(ArtistDAO theArtistDao) {
        artistDAO = theArtistDao;
    }

    public void addArtist(Artist theArtist) {
        artistDAO.save(theArtist);
    }

    public ArrayList<Artist> getAllArtist() {
        return (ArrayList<Artist>) artistDAO.findAll();
    }

    public Artist getArtist(Long id) {
        return artistDAO.findById(id).get();
    }

    public void deleteArtist(Long id) {
        artistDAO.deleteById(id);
    }

    public ArrayList<Artist> getAllArtistByGenre(String genre) {
        return artistDAO.findArtistsByGenre(genre);
    }

    public ArrayList<Artist> getAllArtistByYear(int year) {
        return artistDAO.findArtistsByFormedAfter(year);
    }

    public void addMultipleArtists(List<Artist> artists) {
        artistDAO.saveAll(artists);
    }
}
