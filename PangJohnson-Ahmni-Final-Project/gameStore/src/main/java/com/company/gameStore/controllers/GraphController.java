package com.company.gameStore.controllers;

import com.company.gameStore.models.Console;
import com.company.gameStore.models.Game;
import com.company.gameStore.models.TShirt;
import com.company.gameStore.repositories.ConsoleRepository;
import com.company.gameStore.repositories.GameRepository;
import com.company.gameStore.repositories.TShirtRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.graphql.data.method.annotation.Argument;
import org.springframework.graphql.data.method.annotation.QueryMapping;
import org.springframework.stereotype.Controller;

import java.util.List;
import java.util.Optional;

@Controller
public class GraphController {
    @Autowired
    GameRepository gameRepository;
    @Autowired
    ConsoleRepository ConsoleRepository;
    @Autowired
    TShirtRepository TShirtRepository;

    @QueryMapping
    public Optional<Game> findGameById(@Argument int id) {
        return gameRepository.findById(id);
    }
    @QueryMapping
    public Optional<Console> findConsoleById(@Argument int id) {
        return ConsoleRepository.findById(id);
    }
    @QueryMapping
    public Optional<List<Console>> findConsoleByManu(@Argument String manu) {
        return Optional.of(ConsoleRepository.findByManufacturer(manu));
    }
    @QueryMapping
    public Optional<List<Game>> findGameByESRB(@Argument String esrb) {
        return gameRepository.findByEsrbRating(esrb);
    }
    @QueryMapping
    public Optional<List<Game>> findGameByStudio(@Argument String studio) {
        return gameRepository.findByStudio(studio);
    }
    @QueryMapping
    public Optional<Game> findGameByTitle(@Argument String title) {
        return gameRepository.findByTitle(title);
    }
    @QueryMapping
    public List<Game> findAllGames() {
        return gameRepository.findAll();
    }

    @QueryMapping
    public List<Console> findAllConsoles() {
        return ConsoleRepository.findAll();
    }
}
