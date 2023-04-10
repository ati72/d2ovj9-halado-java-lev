package com.musicians.d2ovj9haladojavalev.controller;

import com.musicians.d2ovj9haladojavalev.entity.Publisher;
import com.musicians.d2ovj9haladojavalev.service.PublisherService;
import jakarta.validation.Valid;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;

@RestController
@RequestMapping("/api")
public class PublisherController {
    private final PublisherService publisherService;
    public PublisherController(PublisherService publisherService) {
        this.publisherService = publisherService;
    }

    @GetMapping("/publisher")
    public ArrayList<Publisher> getAllPublisher() {
        return publisherService.getAllPublisher();
    }

    @GetMapping("/publisher/{id}")
    public Publisher getPublisherById(@PathVariable Long id) {
        return publisherService.getPublisherById(id);
    }

    @PostMapping("/publisher")
    public Publisher addPublisher(@Valid @RequestBody Publisher publisher, BindingResult bindingResult) {
        if (bindingResult.hasErrors()) {
            System.out.println(bindingResult.getAllErrors());
        }
        publisherService.addPublisher(publisher);
        return publisher;
    }

    @DeleteMapping("/publisher/{id}")
    public void deletePublisherById(Long id) {
        publisherService.deletePublisherById(id);
    }

}
