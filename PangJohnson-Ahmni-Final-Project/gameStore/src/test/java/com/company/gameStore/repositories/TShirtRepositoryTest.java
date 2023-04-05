package com.company.gameStore.repositories;

import com.company.gameStore.models.TShirt;
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
public class TShirtRepositoryTest {

    @Autowired
    TShirtRepository tShirtRepository;

    @Before
    public void setUp() {
        tShirtRepository.deleteAll();
    }

    @Test
    public void shouldAddTShirt() {
        // Arrange
        TShirt tShirt = new TShirt();
        tShirt.setSize("Large");
        tShirt.setColor("Blue");
        tShirt.setDescription("A blue t-shirt");
        tShirt.setPrice(new BigDecimal("19.99"));
        tShirt.setQuantity(20);

        // Act
        tShirtRepository.save(tShirt);

        // Assert
        assertEquals(1, tShirtRepository.findAll().size());
    }

    @Test
    public void shouldFindTShirtById() {
        // Arrange
        TShirt tShirt = new TShirt();
        tShirt.setSize("Large");
        tShirt.setColor("Blue");
        tShirt.setDescription("A blue t-shirt");
        tShirt.setPrice(new BigDecimal("19.99"));
        tShirt.setQuantity(20);

        // Act
        tShirtRepository.save(tShirt);
        TShirt tShirtFromDb = tShirtRepository.findById(tShirt.getId()).orElse(null);

        // Assert
        assertEquals(tShirt, tShirtFromDb);
    }

    @Test
    public void shouldGetAllTShirts() {
        // Arrange
        TShirt tShirt = new TShirt();
        tShirt.setSize("Large");
        tShirt.setColor("Blue");
        tShirt.setDescription("A blue t-shirt");
        tShirt.setPrice(new BigDecimal("19.99"));
        tShirt.setQuantity(20);

        TShirt tShirt2 = new TShirt();
        tShirt2.setSize("Small");
        tShirt2.setColor("Red");
        tShirt2.setDescription("A red t-shirt");
        tShirt2.setPrice(new BigDecimal("14.99"));
        tShirt2.setQuantity(15);

        // Act
        tShirtRepository.save(tShirt);
        tShirtRepository.save(tShirt2);

        // Assert
        assertEquals(2, tShirtRepository.findAll().size());
    }

    @Test
    public void shouldUpdateTShirt() {
        // Arrange
        TShirt tShirt = new TShirt();
        tShirt.setSize("Large");
        tShirt.setColor("Blue");
        tShirt.setDescription("A blue t-shirt");
        tShirt.setPrice(new BigDecimal("19.99"));
        tShirt.setQuantity(20);

        // Act
        tShirtRepository.save(tShirt);
        tShirt.setQuantity(15);
        tShirt.setColor("Green");
        tShirt.setPrice(new BigDecimal("24.99"));
        tShirtRepository.save(tShirt);
        TShirt tShirtFromDb = tShirtRepository.findById(tShirt.getId()).orElse(new TShirt());

        // Assert
        assertEquals(15, tShirtFromDb.getQuantity());
        assertEquals("Green", tShirtFromDb.getColor());
        assertEquals(new BigDecimal("24.99"), tShirtFromDb.getPrice());
    }

    @Test
    public void shouldFindTShirtByColor() {
        // Arrange
        TShirt tShirt = new TShirt();
        tShirt.setSize("Large");
        tShirt.setColor("Blue");
        tShirt.setDescription("A blue t-shirt");
        tShirt.setPrice(new BigDecimal("19.99"));
        tShirt.setQuantity(20);

        TShirt tShirt2 = new TShirt();
        tShirt2.setSize("Medium");
        tShirt2.setColor("Green");
        tShirt2.setDescription("A green t-shirt");
        tShirt2.setPrice(new BigDecimal("24.99"));
        tShirt2.setQuantity(15);

        // Act
        tShirtRepository.save(tShirt);
        tShirtRepository.save(tShirt2);
        TShirt tShirtFromDb = tShirtRepository.findByColor("Blue").get(0);

        // Assert
        assertEquals(tShirt, tShirtFromDb);
    }

    @Test
    public void shouldDeleteTShirt() {
        // Arrange
        TShirt tShirt = new TShirt();
        tShirt.setSize("Large");
        tShirt.setColor("Blue");
        tShirt.setDescription("A blue t-shirt");
        tShirt.setPrice(new BigDecimal("19.99"));
        tShirt.setQuantity(20);

        // Act
        tShirtRepository.save(tShirt);
        tShirtRepository.delete(tShirt);
        TShirt tShirtFromDb = tShirtRepository.findById(tShirt.getId()).orElse(null);

        // Assert
        assertNull(tShirtFromDb);
    }

    @Test
    public void shouldFindTShirtBySize() {
        // Arrange
        TShirt tShirt = new TShirt();
        tShirt.setSize("Large");
        tShirt.setColor("Blue");
        tShirt.setDescription("A blue t-shirt");
        tShirt.setPrice(new BigDecimal("19.99"));
        tShirt.setQuantity(20);

        TShirt tShirt2 = new TShirt();
        tShirt2.setSize("Medium");
        tShirt2.setColor("Green");
        tShirt2.setDescription("A green t-shirt");
        tShirt2.setPrice(new BigDecimal("24.99"));
        tShirt2.setQuantity(15);

        // Act
        tShirtRepository.save(tShirt);
        tShirtRepository.save(tShirt2);
        TShirt tShirtFromDb = tShirtRepository.findBySize("Large").get(0);

        // Assert
        assertEquals(tShirt, tShirtFromDb);
    }




}