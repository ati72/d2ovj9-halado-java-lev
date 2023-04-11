package com.musicians.d2ovj9haladojavalev.service;

import com.musicians.d2ovj9haladojavalev.entity.Album;
import com.musicians.d2ovj9haladojavalev.persist.AlbumDAO;
import org.springframework.stereotype.Service;

import java.util.ArrayList;

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
        return albumDAO.findById(id).get();
    }

    public void addAlbum(Album album) {
        albumDAO.save(album);
    }

    public void deleteAlbumById(Long id) {
        albumDAO.deleteById(id);
    }
}
