package com.company.gameStore.controllers;

import com.company.gameStore.models.Game;
import com.company.gameStore.repositories.GameRepository;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.Before;
import org.junit.Test;
import org.junit.jupiter.api.BeforeEach;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import static org.mockito.Mockito.doReturn;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.delete;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.put;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;

@RunWith(SpringRunner.class)
@WebMvcTest(GameController.class)
public class GameControllerTests {

    @MockBean
    GameRepository gameRepo;
    // Wiring in the MockMvc object
    @Autowired
    private MockMvc mockMvc;

    // ObjectMapper used to convert Java objects to JSON and vice versa
    private ObjectMapper mapper = new ObjectMapper();

    // A list of records for testing purposes
//    @BeforeEach
//    void setUp(){
//        gameRepo = Mockito.mock(GameRepository.class);
//    }
    // Testing GET /records
    @Test
    public void shouldReturnAllGames() throws Exception {

        // ARRANGE
        // Convert Java object to JSON
        // ACT
        mockMvc.perform(get("/games"))                // Perform the GET request
                .andDo(print())                          // Print results to console
                .andExpect(status().isOk());              // ASSERT (status code is 200)
    }

    // Testing POST /records
    @Test
    public void shouldReturnNewGameOnPostRequest() throws Exception {

        // ARRANGE
        Game inputGame = new Game("Minecraft", "E",
                "Minecraft is a game", 7.25, "Mojang");
        inputGame.setId(0);
        when(gameRepo.save(inputGame)).thenReturn(inputGame);
        // Convert Java Object to JSON
        String inputJson = mapper.writeValueAsString(inputGame);

        // ACT
        mockMvc.perform(
                        post("/games")                            // Perform the POST request
                                .content(inputJson)                       // Set the request body
                                .contentType(MediaType.APPLICATION_JSON)  // Tell the server it's in JSON format
                )
                .andDo(print())                                // Print results to console
                .andExpect(status().isCreated());              // ASSERT (status code is 201)
    }

    // Testing GET record/{id}
    @Test
    public void shouldReturnGameByStudio() throws Exception {

        Game outputGame = new Game("Minecraft", "E",
                "Minecraft is a game", 7.25, "Mojang");
        outputGame.setId(0);
        List<Game> gameList = new ArrayList<>();
        gameList.add(outputGame);
        when(gameRepo.findByStudio(outputGame.getStudio())).thenReturn(Optional.of(gameList));
        String outputJson = mapper.writeValueAsString(outputGame);

        mockMvc.perform(get("/games/studio/Mojang"))
                .andDo(print())
                .andExpect(status().isOk());
    }

    @Test
    public void shouldReturnGameByESRB() throws Exception {

        Game outputGame = new Game("Minecraft", "E",
                "Minecraft is a game", 7.25, "Mojang");
        outputGame.setId(0);
        List<Game> gameList = new ArrayList<>();
        gameList.add(outputGame);
        when(gameRepo.findByEsrbRating(outputGame.getEsrbRating())).thenReturn(Optional.of(gameList));
        String outputJson = mapper.writeValueAsString(outputGame);

        mockMvc.perform(get("/games/esrb/E"))
                .andDo(print())
                .andExpect(status().isOk());
    }

    @Test
    public void shouldReturnGameByTitle() throws Exception {

        Game outputGame = new Game("Minecraft", "E",
                "Minecraft is a game", 7.25, "Mojang");
        outputGame.setId(0);
        when(gameRepo.findByTitle(outputGame.getTitle())).thenReturn(Optional.of(outputGame));
        String outputJson = mapper.writeValueAsString(outputGame);

        mockMvc.perform(get("/games/title/Minecraft"))
                .andDo(print())
                .andExpect(status().isOk());
    }

    // Testing PUT /records/{id}
    @Test
    public void shouldUpdateAndReturn204StatusCode() throws Exception {

        // This method returns nothing, so we're just checking for correct status code
        // In this case, code 204, which indicates No Content

        Game inputGame = new Game("Minecraft", "E",
                "Minecraft is a game", 7.25, "Mojang");
        inputGame.setId(0);
        when(gameRepo.findById(0)).thenReturn(Optional.of(inputGame));
        when(gameRepo.save(inputGame)).thenReturn(inputGame);
        String inputJson = mapper.writeValueAsString(inputGame);

        mockMvc.perform(
                        put("/games/{id}", 0)
                                .content(inputJson)
                                .contentType(MediaType.APPLICATION_JSON)
                )
                .andDo(print())
                .andExpect(status().isNoContent());
    }

    // Testing DELETE /records/{id}
    @Test
    public void shouldDeleteByIdAndReturn204StatusCode() throws Exception {
        Game inputGame = new Game("Minecraft", "E",
                "Minecraft is a game", 7.25, "Mojang");
        when(gameRepo.findById(0)).thenReturn(Optional.of(inputGame));
        // This method returns nothing, so we're just checking for correct status code
        // In this case, code 204, which indicates No Content
        mockMvc.perform(delete("/games/0"))
                .andDo(print())
                .andExpect(status().isNoContent());
    }
}
