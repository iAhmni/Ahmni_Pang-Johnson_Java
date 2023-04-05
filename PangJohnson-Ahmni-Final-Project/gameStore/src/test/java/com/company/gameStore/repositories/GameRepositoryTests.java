package com.company.gameStore.repositories;

import com.company.gameStore.models.Game;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.List;
import java.util.Optional;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;

@RunWith(SpringRunner.class)
@SpringBootTest
public class  GameRepositoryTests {
    @Autowired
    GameRepository gameRepo;
    @Before
    public void setUp() throws Exception {
        gameRepo.deleteAll();
    }

    @Test
    public void AddGame(){
        Game game = new Game("Minecraft", "E",
                "Minecraft is a game", 7.25, "Mojang");
        gameRepo.save(game);
        Optional<Game> game1 = gameRepo.findById(game.getId());

        assertEquals(game1.get(), game);
    }

    @Test
    public void getGameByStudio(){
        Game game = new Game("Minecraft", "E",
                "Minecraft is a game", 7.25, "Mojang");
        Game game2 = new Game("Apex Legends", "M",
                "Apex Legends is a game", 7.25, "EA");
        gameRepo.save(game);
        gameRepo.save(game2);
        Optional<List<Game>> game1 = gameRepo.findByStudio(game.getStudio());

        assertEquals(game1.get().size(), 1);
    }

    @Test
    public void getGameByESRB(){
        Game game = new Game("Minecraft", "E",
                "Minecraft is a game", 7.25, "Mojang");
        Game game2 = new Game("Apex Legends", "M",
                "Apex Legends is a game", 7.25, "EA");
        gameRepo.save(game);
        gameRepo.save(game2);
        Optional<List<Game>> game1 = gameRepo.findByEsrbRating(game.getEsrbRating());

        assertEquals(game1.get().size(), 1);
    }

    @Test
    public void getGameByTitle(){
        Game game = new Game("Minecraft", "E",
                "Minecraft is a game", 7.25, "Mojang");
        Game game2 = new Game("Apex Legends", "M",
                "Apex Legends is a game", 7.25, "EA");
        gameRepo.save(game);
        gameRepo.save(game2);
        Optional<Game> game1 = gameRepo.findByTitle(game.getTitle());

        assertEquals(game1.get(), game);
    }

    @Test
    public void updateGame(){
        Game game = new Game("Minecraft", "E",
                "Minecraft is a game", 7.25, "Mojang");
        gameRepo.save(game);
        game.setTitle("Updated");
        gameRepo.save(game);
        Optional<Game> game1 = gameRepo.findById(game.getId());

        assertEquals(game1.get(), game);
    }

    @Test
    public void deleteGame(){
        Game game = new Game("Minecraft", "E",
                "Minecraft is a game", 7.25, "Mojang");
        gameRepo.save(game);
        gameRepo.deleteById(game.getId());
        Optional<Game> game1 = gameRepo.findById(game.getId());
        assertFalse(game1.isPresent());
    }

}
