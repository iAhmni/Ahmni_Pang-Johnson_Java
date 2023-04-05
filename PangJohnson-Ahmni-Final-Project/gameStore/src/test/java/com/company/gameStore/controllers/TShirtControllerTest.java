package com.company.gameStore.controllers;

import com.company.gameStore.models.TShirt;
import com.company.gameStore.repositories.TShirtRepository;
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
@WebMvcTest(TShirtController.class)
public class TShirtControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private TShirtRepository tShirtRepository;

    private final ObjectMapper mapper = new ObjectMapper();

    @Test
    public void shouldAddTShirt() throws Exception {
        // Arrange
        TShirt tShirt = new TShirt();
        tShirt.setSize("L");
        tShirt.setColor("Red");
        tShirt.setDescription("Cool T-Shirt");
        tShirt.setPrice(new BigDecimal("19.99"));
        tShirt.setQuantity(10);

        TShirt mockTShirt = new TShirt();
        mockTShirt.setId(1);
        mockTShirt.setSize("L");
        mockTShirt.setColor("Red");
        mockTShirt.setDescription("Cool T-Shirt");
        mockTShirt.setPrice(new BigDecimal("19.99"));
        mockTShirt.setQuantity(10);

        // Mock
        doReturn(mockTShirt).when(tShirtRepository).save(tShirt);

        String tShirtJson = mapper.writeValueAsString(tShirt);

        // Act
        mockMvc.perform(post("/tshirts")
                        .content(tShirtJson)
                        .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isCreated());
    }

    @Test
    public void shouldGetTShirtById() throws Exception {
        // Arrange
        TShirt tShirt = new TShirt();
        tShirt.setId(1);
        tShirt.setSize("L");
        tShirt.setColor("Red");
        tShirt.setDescription("Cool T-Shirt");
        tShirt.setPrice(new BigDecimal("19.99"));
        tShirt.setQuantity(10);

        // Mock
        doReturn(Optional.of(tShirt)).when(tShirtRepository).findById(1);

        // Act
        mockMvc.perform(get("/tshirts/1"))
                .andExpect(status().isOk());
    }

    @Test
    public void shouldGetAllTShirts() throws Exception {
        // Arrange
        TShirt tshirt1 = new TShirt();
        tshirt1.setId(1);
        tshirt1.setSize("M");
        tshirt1.setColor("Black");
        tshirt1.setDescription("Black T-Shirt");
        tshirt1.setPrice(new BigDecimal("19.99"));
        tshirt1.setQuantity(20);

        TShirt tshirt2 = new TShirt();
        tshirt2.setId(2);
        tshirt2.setSize("L");
        tshirt2.setColor("White");
        tshirt2.setDescription("White T-Shirt");
        tshirt2.setPrice(new BigDecimal("24.99"));
        tshirt2.setQuantity(15);

        List<TShirt> tshirtList = Arrays.asList(tshirt1, tshirt2);

        // Mock
        doReturn(tshirtList).when(tShirtRepository).findAll();

        // Act
        mockMvc.perform(get("/tshirts"))
                .andExpect(status().isOk());
    }

    @Test
    public void shouldUpdateTShirt() throws Exception {
        // Arrange
        TShirt tshirt = new TShirt();
        tshirt.setId(1);
        tshirt.setSize("M");
        tshirt.setColor("Black");
        tshirt.setDescription("Black T-Shirt");
        tshirt.setPrice(new BigDecimal("19.99"));
        tshirt.setQuantity(20);

        TShirt mockTShirt = new TShirt();
        mockTShirt.setId(1);
        mockTShirt.setSize("M");
        mockTShirt.setColor("Black");
        mockTShirt.setDescription("Black T-Shirt");
        mockTShirt.setPrice(new BigDecimal("19.99"));
        mockTShirt.setQuantity(20);

        // Mock
        doReturn(mockTShirt).when(tShirtRepository).save(tshirt);
        String tshirtAsString = mapper.writeValueAsString(tshirt);

        // Act ...
        mockMvc.perform(put("/tshirts")
                        .content(tshirtAsString)
                        .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isNoContent());
    }

    @Test
    public void shouldDeleteTShirt() throws Exception {
        // Act
        mockMvc.perform(delete("/tshirts/1"))
                .andExpect(status().isNoContent());
    }

    @Test
    public void shouldGetTShirtByColor() throws Exception {
        // Arrange
        TShirt tshirt = new TShirt();
        tshirt.setId(1);
        tshirt.setSize("M");
        tshirt.setColor("Red");
        tshirt.setDescription("A comfortable red t-shirt.");
        tshirt.setPrice(new BigDecimal("19.99"));
        tshirt.setQuantity(10);

        TShirt tshirt2 = new TShirt();
        tshirt2.setId(2);
        tshirt2.setSize("L");
        tshirt2.setColor("Red");
        tshirt2.setDescription("A comfortable large red t-shirt.");
        tshirt2.setPrice(new BigDecimal("21.99"));
        tshirt2.setQuantity(5);

        List<TShirt> tshirtList = Arrays.asList(tshirt, tshirt2);

        // Mock
        doReturn(tshirtList).when(tShirtRepository).findByColor("Red");

        // Act
        mockMvc.perform(get("/tshirts/color/Red"))
                .andExpect(status().isOk());
    }

    @Test
    public void shouldGetTShirtBySize() throws Exception {
        // Arrange
        TShirt tshirt = new TShirt();
        tshirt.setId(1);
        tshirt.setSize("M");
        tshirt.setColor("Red");
        tshirt.setDescription("A comfortable red t-shirt.");
        tshirt.setPrice(new BigDecimal("19.99"));
        tshirt.setQuantity(10);

        TShirt tshirt2 = new TShirt();
        tshirt2.setId(2);
        tshirt2.setSize("L");
        tshirt2.setColor("Blue");
        tshirt2.setDescription("A comfortable large blue t-shirt.");
        tshirt2.setPrice(new BigDecimal("21.99"));
        tshirt2.setQuantity(5);

        List<TShirt> tshirtList = Arrays.asList(tshirt, tshirt2);

        // Mock
        doReturn(tshirtList).when(tShirtRepository).findBySize("M");

        // Act
        mockMvc.perform(get("/tshirts/size/M"))
                .andExpect(status().isOk());
    }

}

