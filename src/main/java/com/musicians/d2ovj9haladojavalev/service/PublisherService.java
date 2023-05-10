package com.musicians.d2ovj9haladojavalev.service;

import com.musicians.d2ovj9haladojavalev.dto.PublisherDTO;
import com.musicians.d2ovj9haladojavalev.entity.Artist;
import com.musicians.d2ovj9haladojavalev.entity.Publisher;
import com.musicians.d2ovj9haladojavalev.exception.DataNotFoundException;
import com.musicians.d2ovj9haladojavalev.persist.PublisherDAO;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class PublisherService {
    private final PublisherDAO publisherDAO;

    public PublisherService(PublisherDAO publisherDAO) {
        this.publisherDAO = publisherDAO;
    }

    public ArrayList<Publisher> getAllPublisher() {
        ArrayList<Publisher> publishers = (ArrayList<Publisher>) publisherDAO.findAll();
        if (publishers.isEmpty()) {
            throw new DataNotFoundException(
                    "Whooops. No publishers found in database."
            );
        }
        return publishers;
    }

    public Publisher getPublisherById(Long id) {
        return publisherDAO.findById(id)
                .orElseThrow(() -> new DataNotFoundException(
                        "Whoops. No publisher found with id: " + id
                ));
    }

    public void addPublisher(Publisher publisher) {
        publisherDAO.save(publisher);
    }

    public void deletePublisherById(Long id) {
        publisherDAO.deleteById(id);
    }

    public List<PublisherDTO> getAllPublishersDto() {
        List<Publisher> publisherList = (List<Publisher>) publisherDAO.findAll();
        if (publisherList.isEmpty()) {
            throw new DataNotFoundException(
                    "Whoops. No publishers found in database."
            );
        }
        return publisherList.stream()
                .map(publisher -> new PublisherDTO(
                        publisher.getId(),
                        publisher.getName(),
                        publisher.getLocation(),
                        publisher.getArtists()
                                .stream()
                                .map(Artist::getName)
                                .collect(Collectors.toList())
                )).collect(Collectors.toList());
    }
}
