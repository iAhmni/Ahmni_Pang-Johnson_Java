package com.company.bookstore.Controller;

import com.company.bookstore.Model.Publisher;
import com.company.bookstore.Repository.PublisherRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
public class PublisherController {
    @Autowired
    PublisherRepository repo;

    @GetMapping("/publishers")
    public List<Publisher> getPublishers() {
        return repo.findAll();
    }

    @GetMapping("/publishers/{id}")
    public Publisher getPublisherById(@PathVariable int id) {

        Optional<Publisher> returnVal = repo.findById(id);
        if (returnVal.isPresent()) {
            return returnVal.get();
        } else {
            return null;
        }
    }

    @PostMapping("/publishers")
    @ResponseStatus(HttpStatus.CREATED)
    public Publisher addPublisher(@RequestBody Publisher Publisher) {
        return repo.save(Publisher);
    }

    @PutMapping("/publishers")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void updatePublisher(@RequestBody Publisher Publisher) {
        repo.save(Publisher);
    }

    @DeleteMapping("/publishers/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void deletePublisher(@PathVariable int id) {
        repo.deleteById(id);
    }
}
