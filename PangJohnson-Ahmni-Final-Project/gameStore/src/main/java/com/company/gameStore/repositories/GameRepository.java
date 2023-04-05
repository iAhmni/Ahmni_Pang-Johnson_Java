package com.company.gameStore.repositories;

import com.company.gameStore.models.Game;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

public interface GameRepository extends JpaRepository<Game, Integer> {
    Optional<List<Game>> findByStudio(String studio);
    Optional<List<Game>> findByEsrbRating(String esrb_rating);
    Optional<Game> findByTitle(String title);
}
