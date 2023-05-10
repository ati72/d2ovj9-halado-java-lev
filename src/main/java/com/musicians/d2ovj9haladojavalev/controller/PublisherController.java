package com.musicians.d2ovj9haladojavalev.controller;

import com.musicians.d2ovj9haladojavalev.dto.PublisherDTO;
import com.musicians.d2ovj9haladojavalev.entity.Artist;
import com.musicians.d2ovj9haladojavalev.entity.Publisher;
import com.musicians.d2ovj9haladojavalev.service.ArtistService;
import com.musicians.d2ovj9haladojavalev.service.PublisherService;
import jakarta.validation.Valid;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;

@RestController
@RequestMapping("/api/v1/publisher")
public class PublisherController {
    private final PublisherService publisherService;
    private final ArtistService artistService;
    public PublisherController(PublisherService publisherService, ArtistService artistService) {
        this.publisherService = publisherService;
        this.artistService = artistService;
    }

    @GetMapping
    public ArrayList<Publisher> getAllPublisher() {
        return publisherService.getAllPublisher();
    }

    @GetMapping("/dto")
    public List<PublisherDTO> getAllPublishersDto() {
        return publisherService.getAllPublishersDto();
    }

    @GetMapping("/{id}")
    public Publisher getPublisherById(@PathVariable Long id) {
        return publisherService.getPublisherById(id);
    }

    @PostMapping
    public Publisher addPublisher(@Valid @RequestBody Publisher publisher, BindingResult bindingResult) {
        if (bindingResult.hasErrors()) {
            System.out.println(bindingResult.getAllErrors());
        }
        publisherService.addPublisher(publisher);
        return publisher;
    }

    @PutMapping("/{publisherId}/artist/{artistId}")
    public Publisher associatePublisherWithArtist(
            @PathVariable Long publisherId,
            @PathVariable Long artistId
    ) {
        Publisher publisher = publisherService.getPublisherById(publisherId);
        Artist artist = artistService.getArtist(artistId);
        publisher.addArtists(artist);
        publisherService.addPublisher(publisher);
        return publisher;
    }

    @DeleteMapping("/{id}")
    public void deletePublisherById(@PathVariable Long id) {
        publisherService.deletePublisherById(id);
    }

}
