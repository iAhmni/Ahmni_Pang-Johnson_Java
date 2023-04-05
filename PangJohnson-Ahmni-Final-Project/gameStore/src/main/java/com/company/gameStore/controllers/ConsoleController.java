package com.company.gameStore.controllers;

import com.company.gameStore.models.Console;
import com.company.gameStore.repositories.ConsoleRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;

import javax.validation.Valid;
import java.util.List;
import java.util.Optional;


@RestController
public class ConsoleController {
    private final ConsoleRepository consoleRepository;

    @Autowired
    public ConsoleController(ConsoleRepository consoleRepository) {
        this.consoleRepository = consoleRepository;
    }

    @GetMapping("/consoles")
    public List<Console> getAllConsoles() {
        return consoleRepository.findAll();
    }

    @GetMapping("/consoles/{consoleId}")
    public Console getConsoleById(@PathVariable int consoleId) {
        return consoleRepository.findById(consoleId).orElse(null);
    }

    @PostMapping("/consoles")
    @ResponseStatus(HttpStatus.CREATED)
    public Console addConsole(@RequestBody @Valid Console console) {
        return consoleRepository.save(console);
    }

    @PutMapping("/consoles/{consoleId}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void updateConsole(@PathVariable int consoleId, @RequestBody @Valid Console console) {
        Optional<Console> existingConsole = consoleRepository.findById(consoleId);
        if (existingConsole.isPresent()) {
            console.setId(consoleId);
            consoleRepository.save(console);
        } else {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, "Console not found");
        }
    }

    @DeleteMapping("/consoles/{consoleId}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void deleteConsole(@PathVariable int consoleId) {
        try {
            consoleRepository.deleteById(consoleId);
        } catch (EmptyResultDataAccessException ex) {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, "Console not found", ex);
        }
    }

    @GetMapping("/consoles/manufacturer/{manufacturer}")
    public List<Console> getConsoleByManufacturer(@PathVariable String manufacturer) {
        return consoleRepository.findByManufacturer(manufacturer);
    }
}
