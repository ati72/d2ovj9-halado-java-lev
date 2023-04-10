package com.musicians.d2ovj9haladojavalev.service;

import com.musicians.d2ovj9haladojavalev.entity.Publisher;
import com.musicians.d2ovj9haladojavalev.persist.PublisherDAO;
import org.springframework.stereotype.Service;

import java.util.ArrayList;

@Service
public class PublisherService {
    private final PublisherDAO publisherDAO;

    public PublisherService(PublisherDAO publisherDAO) {
        this.publisherDAO = publisherDAO;
    }

    public ArrayList<Publisher> getAllPublisher() {
        return (ArrayList<Publisher>) publisherDAO.findAll();
    }

    public Publisher getPublisherById(Long id) {
        return publisherDAO.findById(id).get();
    }

    public void addPublisher(Publisher publisher) {
        publisherDAO.save(publisher);
    }

    public void deletePublisherById(Long id) {
        publisherDAO.deleteById(id);
    }
}
