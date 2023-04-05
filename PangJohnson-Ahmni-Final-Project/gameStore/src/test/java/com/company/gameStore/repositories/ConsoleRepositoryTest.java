package com.company.gameStore.repositories;

import com.company.gameStore.models.Console;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.math.BigDecimal;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNull;

@RunWith(SpringRunner.class)
@SpringBootTest
public class ConsoleRepositoryTest {

    @Autowired
    ConsoleRepository consoleRepository;

    @Before
    public void setUp() {
        consoleRepository.deleteAll();
    }

    @Test
    public void shouldAddConsole() {
        // Arrange
        Console console = new Console();
        console.setModel("Playstation 4");
        console.setManufacturer("Sony");
        console.setMemoryAmount("500GB");
        console.setProcessor("AMD Jaguar");
        console.setPrice(new BigDecimal("299.99"));
        console.setQuantity(10);

        // Act
        consoleRepository.save(console);

        // Assert
        assertEquals(1, consoleRepository.findAll().size());
    }


    @Test
    public void shouldFindConsoleById() {
        // Arrange
        Console console = new Console();
        console.setModel("Playstation 4");
        console.setManufacturer("Sony");
        console.setMemoryAmount("500GB");
        console.setProcessor("AMD Jaguar");
        console.setPrice(new BigDecimal("299.99"));
        console.setQuantity(10);

        // Act
        consoleRepository.save(console);
        Console consoleFromDb = consoleRepository.findById(console.getId()).orElse(null);

        // Assert
        assertEquals(console, consoleFromDb);
    }

    @Test
    public void shouldGetAllConsoles() {
        // Arrange
        Console console = new Console();
        console.setModel("Playstation 4");
        console.setManufacturer("Sony");
        console.setMemoryAmount("500GB");
        console.setProcessor("AMD Jaguar");
        console.setPrice(new BigDecimal("299.99"));
        console.setQuantity(10);

        Console console2 = new Console();
        console2.setModel("Xbox One");
        console2.setManufacturer("Microsoft");
        console2.setMemoryAmount("500GB");
        console2.setProcessor("AMD Jaguar");
        console2.setPrice(new BigDecimal("199.69"));
        console2.setQuantity(10);

        // Act
        consoleRepository.save(console);
        consoleRepository.save(console2);

        // Assert
        assertEquals(2, consoleRepository.findAll().size());
    }

    @Test
    public void shouldUpdateConsole() {
        // Arrange
        Console console = new Console();
        console.setModel("Playstation 4");
        console.setManufacturer("Sony");
        console.setMemoryAmount("500GB");
        console.setProcessor("AMD Jaguar");
        console.setPrice(new BigDecimal("299.99"));
        console.setQuantity(10);

        // Act
        consoleRepository.save(console);
        console.setQuantity(5);
        console.setManufacturer("Microsoft");
        console.setPrice(new BigDecimal("399.99"));
        consoleRepository.save(console);
        Console consoleFromDb = consoleRepository.findById(console.getId()).orElse(new Console());

        // Assert
        assertEquals(5, consoleFromDb.getQuantity());
        assertEquals("Microsoft", consoleFromDb.getManufacturer());
        assertEquals(new BigDecimal("399.99"), consoleFromDb.getPrice());
    }

    @Test
    public void shouldFindConsoleByManufacturer() {
        // Arrange
        Console console = new Console();
        console.setModel("Playstation 4");
        console.setManufacturer("Sony");
        console.setMemoryAmount("500GB");
        console.setProcessor("AMD Jaguar");
        console.setPrice(new BigDecimal("299.99"));
        console.setQuantity(8);

        Console console2 = new Console();
        console2.setModel("Xbox One");
        console2.setManufacturer("Microsoft");
        console2.setMemoryAmount("500GB");
        console2.setProcessor("AMD Jaguar");
        console2.setPrice(new BigDecimal("199.69"));
        console2.setQuantity(10);

        // Act
        consoleRepository.save(console);
        consoleRepository.save(console2);
        Console consoleFromDb = consoleRepository.findByManufacturer("Sony").get(0);

        // Assert
        assertEquals(console, consoleFromDb);
    }

    @Test
    public void shouldDeleteConsole() {
        // Arrange
        Console console = new Console();
        console.setModel("Playstation 4");
        console.setManufacturer("Sony");
        console.setMemoryAmount("500GB");
        console.setProcessor("AMD Jaguar");
        console.setPrice(new BigDecimal("299.99"));
        console.setQuantity(10);

        // Act
        consoleRepository.save(console);
        consoleRepository.delete(console);
        Console consoleFromDb = consoleRepository.findById(console.getId()).orElse(null);

        // Assert
        assertNull(consoleFromDb);
    }
}

