package com.company.gameStore.controllers;

import com.company.gameStore.models.Game;
import com.company.gameStore.repositories.GameRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
public class GameController {
        @Autowired
        private GameRepository repo;

        @GetMapping("/games")
        public List<Game> getGames() {
            return repo.findAll();
        }

        @GetMapping("/games/studio/{studio}")
        public ResponseEntity<List<Game>> getGameByStudio(@PathVariable String studio) {
            Optional<List<Game>> returnVal = repo.findByStudio(studio);
            if (returnVal.isPresent()) {
                return ResponseEntity.ok(returnVal.get());
            } else {
                return ResponseEntity.notFound().build();
            }
        }

        @GetMapping("/games/esrb/{esrb}")
        public ResponseEntity<List<Game>> getGameByESRB(@PathVariable String esrb) {
            Optional<List<Game>> returnVal = repo.findByEsrbRating(esrb);
            if (returnVal.isPresent()) {
                return ResponseEntity.ok(returnVal.get());
            } else {
                return ResponseEntity.notFound().build();
            }
        }

        @GetMapping("/games/title/{title}")
        public ResponseEntity<Game> getGameByTitle(@PathVariable String title) {
            Optional<Game> returnVal = repo.findByTitle(title);
            if (returnVal.isPresent()) {
                return ResponseEntity.ok(returnVal.get());
            } else {
                return ResponseEntity.notFound().build();
            }
        }

        @PostMapping("/games")
        public ResponseEntity<Game> addGame(@RequestBody Game game) {
            Game savedGame = repo.save(game);
            return ResponseEntity.status(HttpStatus.CREATED).body(savedGame);
        }

        @PutMapping("/games/{id}")
        public ResponseEntity<Void> updateGame(@PathVariable int id, @RequestBody Game game) {
            Optional<Game> existingGame = repo.findById(id);
            if (existingGame.isPresent()) {
                game.setId(id);
                repo.save(game);
                return ResponseEntity.noContent().build();
            } else {
                return ResponseEntity.notFound().build();
            }
        }

        @DeleteMapping("/games/{id}")
        public ResponseEntity<Void> deleteGame(@PathVariable int id) {
            Optional<Game> existingGame = repo.findById(id);
            if (existingGame.isPresent()) {
                repo.deleteById(id);
                return ResponseEntity.noContent().build();
            } else {
                return ResponseEntity.notFound().build();
            }
        }
    }

