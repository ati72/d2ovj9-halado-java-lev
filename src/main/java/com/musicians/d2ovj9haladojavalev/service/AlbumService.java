package com.musicians.d2ovj9haladojavalev.service;

import com.musicians.d2ovj9haladojavalev.dto.AlbumDTO;
import com.musicians.d2ovj9haladojavalev.entity.Album;
import com.musicians.d2ovj9haladojavalev.exception.DataNotFoundException;
import com.musicians.d2ovj9haladojavalev.persist.AlbumDAO;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class AlbumService {

    private final AlbumDAO albumDAO;

    public AlbumService(AlbumDAO albumDAO) {
        this.albumDAO = albumDAO;
    }

    public ArrayList<Album> getAllAlbum() {
        return (ArrayList<Album>) albumDAO.findAll();
    }

    public Album getAlbumById(Long id) {
        return albumDAO.findById(id)
                .orElseThrow(() -> new DataNotFoundException(
                        "Whoops. Cannot find album with id: " + id
                        ));
    }

    public void addAlbum(Album album) {
        albumDAO.save(album);
    }

    public void deleteAlbumById(Long id) {
        albumDAO.deleteById(id);
    }

    public List<AlbumDTO> getAllAlbumDto() {
        List<Album> albumList = (List<Album>) albumDAO.findAll();
        return albumList
                .stream()
                .map(album -> new AlbumDTO(
                        album.getId(),
                        album.getArtist() == null ? "No data"
                                :album.getArtist().getName(),
                        album.getTitle(),
                        album.getReleaseYear()
                )).collect(Collectors.toList());
    }
}
