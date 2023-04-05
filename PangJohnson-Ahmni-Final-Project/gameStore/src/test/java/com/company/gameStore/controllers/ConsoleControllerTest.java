package com.company.gameStore.controllers;

import com.company.gameStore.models.Console;
import com.company.gameStore.repositories.ConsoleRepository;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;

import java.math.BigDecimal;
import java.util.Arrays;
import java.util.List;
import java.util.Optional;

import static org.mockito.Mockito.doReturn;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@RunWith(SpringRunner.class)
@WebMvcTest(ConsoleController.class)
public class ConsoleControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private ConsoleRepository consoleRepository;

    private final ObjectMapper mapper = new ObjectMapper();

    @Test
    public void shouldAddConsole() throws Exception {
        // Arrange
        Console console = new Console();
        console.setModel("Playstation 4");
        console.setManufacturer("Sony");
        console.setMemoryAmount("500GB");
        console.setProcessor("AMD Jaguar");
        console.setPrice(new BigDecimal("299.99"));
        console.setQuantity(10);

        Console mockConsole = new Console();
        mockConsole.setId(1);
        mockConsole.setModel("Playstation 4");
        mockConsole.setManufacturer("Sony");
        mockConsole.setMemoryAmount("500GB");
        mockConsole.setProcessor("AMD Jaguar");
        mockConsole.setPrice(new BigDecimal("299.99"));
        mockConsole.setQuantity(10);

        // Mock
        doReturn(mockConsole).when(consoleRepository).save(console);

        String consoleJson = mapper.writeValueAsString(console);

        // Act
        mockMvc.perform(post("/consoles")
                        .content(consoleJson)
                        .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isCreated());
    }

    @Test
    public void shouldGetConsoleById() throws Exception {
        // Arrange
        Console console = new Console();
        console.setId(1);
        console.setModel("Playstation 4");
        console.setManufacturer("Sony");
        console.setMemoryAmount("500GB");
        console.setProcessor("AMD Jaguar");
        console.setPrice(new BigDecimal("299.99"));
        console.setQuantity(10);

        // Mock
        doReturn(Optional.of(console)).when(consoleRepository).findById(1);

        // Act
        mockMvc.perform(get("/consoles/1"))
                .andExpect(status().isOk());
    }

    @Test
    public void shouldGetAllConsoles() throws Exception {
        // Arrange
        Console console = new Console();
        console.setId(1);
        console.setModel("Playstation 4");
        console.setManufacturer("Sony");
        console.setMemoryAmount("500GB");
        console.setProcessor("AMD Jaguar");
        console.setPrice(new BigDecimal("299.99"));
        console.setQuantity(10);

        Console console2 = new Console();
        console2.setId(2);
        console2.setModel("Xbox One");
        console2.setManufacturer("Microsoft");
        console2.setMemoryAmount("500GB");
        console2.setProcessor("AMD Jaguar");
        console2.setPrice(new BigDecimal("299.99"));
        console2.setQuantity(10);

        List<Console> consoleList = Arrays.asList(console, console2);

        // Mock
        doReturn(consoleList).when(consoleRepository).findAll();

        // Act
        mockMvc.perform(get("/consoles"))
                .andExpect(status().isOk());
    }

    @Test
    public void shouldUpdateConsole() throws Exception {
        // Arrange
        Console console = new Console();
        console.setId(1);
        console.setModel("Playstation 4");
        console.setManufacturer("Sony");
        console.setMemoryAmount("500GB");
        console.setProcessor("AMD Jaguar");
        console.setPrice(new BigDecimal("299.99"));
        console.setQuantity(10);

        Console mockConsole = new Console();
        mockConsole.setId(1);
        mockConsole.setModel("Playstation 4");
        mockConsole.setManufacturer("Sony");
        mockConsole.setMemoryAmount("500GB");
        mockConsole.setProcessor("AMD Jaguar");
        mockConsole.setPrice(new BigDecimal("299.99"));
        mockConsole.setQuantity(10);

        // Mock
        doReturn(Optional.of(mockConsole)).when(consoleRepository).findById(1);
        doReturn(mockConsole).when(consoleRepository).save(console);
        String conosleAsString = mapper.writeValueAsString(console);

        // Act ...
        mockMvc.perform(put("/consoles/1")
                        .content(conosleAsString)
                        .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isNoContent());
    }

    @Test
    public void shouldGetConsoleByManufacturer() throws Exception {
        // Arrange
        Console console = new Console();
        console.setId(1);
        console.setModel("Playstation 4");
        console.setManufacturer("Sony");
        console.setMemoryAmount("500GB");
        console.setProcessor("AMD Jaguar");
        console.setPrice(new BigDecimal("299.99"));
        console.setQuantity(10);

        Console console2 = new Console();
        console2.setId(2);
        console2.setModel("Playstation 2");
        console2.setManufacturer("Sony");
        console2.setMemoryAmount("400GB");
        console2.setProcessor("AMD Jaguar");
        console2.setPrice(new BigDecimal("199.99"));
        console2.setQuantity(10);

        List<Console> consoleList = Arrays.asList(console, console2);

        // Mock
        doReturn(consoleList).when(consoleRepository).findByManufacturer("Sony");

        // Act
        mockMvc.perform(get("/consoles/manufacturer/Sony"))
                .andExpect(status().isOk());
    }

    @Test
    public void shouldDeleteConsole() throws Exception {
        // Act
        mockMvc.perform(delete("/consoles/1"))
                .andExpect(status().isNoContent());
    }
}