package com.company.gameStore.controllers;

import com.company.gameStore.models.TShirt;
import com.company.gameStore.repositories.TShirtRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;

import java.util.List;

@RestController
public class TShirtController {
    private final TShirtRepository TShirtRepository;

    @Autowired
    public TShirtController(TShirtRepository TShirtRepository) {
        this.TShirtRepository = TShirtRepository;
    }

    @GetMapping("/tshirts")
    public List<TShirt> getAllTShirts() {
        return TShirtRepository.findAll();
    }

    @GetMapping("/tshirts/{TShirtId}")
    public TShirt getTShirtById(@PathVariable int TShirtId) {
        return TShirtRepository.findById(TShirtId).orElse(null);
    }

    @PostMapping("/tshirts")
    @ResponseStatus(HttpStatus.CREATED)
    public TShirt addTShirt(@RequestBody TShirt TShirt) {
        return TShirtRepository.save(TShirt);
    }

    @PutMapping("/tshirts")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void updateTShirt(@RequestBody TShirt TShirt) {
        TShirtRepository.save(TShirt);
    }

    @DeleteMapping("/tshirts/{TShirtId}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void deleteTShirt(@PathVariable int TShirtId) {
        try {
            TShirtRepository.deleteById(TShirtId);
        } catch (EmptyResultDataAccessException ex) {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, "T-Shirt not found", ex);
        }
    }

    @GetMapping("/tshirts/color/{color}")
    public List<TShirt> getTShirtsByColor(@PathVariable String color) {
        return TShirtRepository.findByColor(color);
    }

    @GetMapping("/tshirts/size/{size}")
    public List<TShirt> getTShirtsBySize(@PathVariable String size) {
        return TShirtRepository.findBySize(size);
    }
}
