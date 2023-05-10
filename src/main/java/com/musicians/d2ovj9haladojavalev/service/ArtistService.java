package com.musicians.d2ovj9haladojavalev.service;


import com.musicians.d2ovj9haladojavalev.dto.ArtistDTO;
import com.musicians.d2ovj9haladojavalev.entity.Album;
import com.musicians.d2ovj9haladojavalev.entity.Artist;
import com.musicians.d2ovj9haladojavalev.entity.Musician;
import com.musicians.d2ovj9haladojavalev.exception.DataNotFoundException;
import com.musicians.d2ovj9haladojavalev.persist.ArtistDAO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class ArtistService {
    private final ArtistDAO artistDAO;

    @Autowired
    public ArtistService(ArtistDAO theArtistDao) {
        artistDAO = theArtistDao;
    }

    public void addArtist(Artist theArtist) {
        artistDAO.save(theArtist);
    }

    public List<Artist> getAllArtist() {
        List<Artist> artists = (List<Artist>) artistDAO.findAll();
        if (artists.isEmpty()) {
            throw new DataNotFoundException(
                    "Whoops. No artists found in database."
            );
        }
        return (List<Artist>) artistDAO.findAll();
    }

    public Artist getArtist(Long id) {
        return artistDAO.findById(id)
                .orElseThrow(() -> new DataNotFoundException(
                        "Whoops. No artist found with id: " + id
                ));
    }

    public void deleteArtist(Long id) {
        artistDAO.deleteById(id);
    }

    public ArrayList<Artist> getAllArtistByGenre(String genre) {
        ArrayList<Artist> artists = artistDAO.findArtistsByGenre(genre);
        if (artists.isEmpty()) {
            throw new DataNotFoundException(
                    "Whoops. No artists found in database with this genre: " + genre
            );
        }
        return artists;
    }

    public ArrayList<Artist> getAllArtistByYear(int year) {
        ArrayList<Artist> artists = artistDAO.findArtistsByFormedAfter(year);
        if (artists.isEmpty()) {
            throw new DataNotFoundException(
                    "Whoops. No artists found in database formed after this year: " + year
            );
        }
        return artists;
    }

    public void addMultipleArtists(List<Artist> artists) {
        artistDAO.saveAll(artists);
    }

    // DTO stuff
    public List<ArtistDTO> getAllArtistDto() {
        List<Artist> artistList = (List<Artist>) artistDAO.findAll();
        if (artistList.isEmpty()) {
            throw new DataNotFoundException(
                    "Whoops. No artists found in database."
            );
        }
        return artistList
                .stream()
                .map(artist -> new ArtistDTO(
                        artist.getId(),
                        artist.getName(),
                        artist.getFormed(),
                        artist.getGenre(),
                        artist.getAlbums()
                                .stream()
                                .map(Album::getTitle)
                                .collect(Collectors.toList()),
                        artist.getPublisher() == null ? "No data" :
                                artist.getPublisher().getName(),
                        artist.getMembers().isEmpty() ? List.of("No data") : // a no daták helyett lehet valami más kéne?
                                artist.getMembers()
                                        .stream()
                                        .map(Musician::getFullName)
                                        .collect(Collectors.toList())
                        )
                )
                .collect(Collectors.toList());
    }

}
