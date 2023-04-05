package com.company.gameStore.controllers;

import com.company.gameStore.models.Invoice;
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
import java.util.ArrayList;
import java.util.List;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@RunWith(SpringRunner.class)
@WebMvcTest(InvoiceController.class)
public class InvoiceControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @MockBean
    InvoiceController invoiceController;
    private final ObjectMapper mapper = new ObjectMapper();
    List<Invoice> invoiceList = new ArrayList<>();

    @Test
    public void shouldReturnCreatedStatus() throws Exception{

        Invoice invoice = new Invoice();
        invoice.setName("Danny Jacobs");
        invoice.setStreet("2423 Maple Ave");
        invoice.setCity("Austin");
        invoice.setState("Texas");
        invoice.setZipcode("94923");
        invoice.setItemType("Game");
        invoice.setItemId(1);
        invoice.setQuantity(10);
        invoice.setUnitPrice(new BigDecimal("19.99"));
        invoice.setSubtotal(new BigDecimal("199.90"));
        invoice.setTax(new BigDecimal("11.99"));
        invoice.setProcessingFee(new BigDecimal("1.95"));
        invoice.setTotal(new BigDecimal("213.84"));

        String inputJson = mapper.writeValueAsString(invoice);

        mockMvc.perform(
                post("/invoice")
                        .content(inputJson)
                        .contentType(MediaType.APPLICATION_JSON))
                .andDo(print())
                .andExpect(status().isCreated());
    }

    @Test
    public void shouldReturnOkStatusOnGet() throws Exception {

        Invoice invoice = new Invoice();
        invoice.setInvoiceId(2);
        invoice.setName("Danny Jacobs");
        invoice.setStreet("2423 Maple Ave");
        invoice.setCity("Austin");
        invoice.setState("Texas");
        invoice.setZipcode("94923");
        invoice.setItemType("Game");
        invoice.setItemId(1);
        invoice.setQuantity(10);
        invoice.setUnitPrice(new BigDecimal("19.99"));
        invoice.setSubtotal(new BigDecimal("199.90"));
        invoice.setTax(new BigDecimal("11.99"));
        invoice.setProcessingFee(new BigDecimal("1.95"));
        invoice.setTotal(new BigDecimal("213.84"));

        String inputJson = mapper.writeValueAsString(invoice);

        mockMvc.perform(
                get("/invoice/2")
                        .content(inputJson)
                        .contentType(MediaType.APPLICATION_JSON))
                .andDo(print())
                .andExpect(status().isOk());
    }

    @Test
    public void shouldReturnOkStatusOnGetAll() throws Exception {

        Invoice invoice1 = new Invoice();
        invoice1.setName("Mark Jacobs");
        invoice1.setStreet("0593 Allen Ave");
        invoice1.setCity("San Diego");
        invoice1.setState("California");
        invoice1.setZipcode("94923");
        invoice1.setItemType("Game");
        invoice1.setItemId(1);
        invoice1.setQuantity(10);
        invoice1.setUnitPrice(new BigDecimal("19.99"));
        invoice1.setSubtotal(new BigDecimal("199.90"));
        invoice1.setTax(new BigDecimal("11.99"));
        invoice1.setProcessingFee(new BigDecimal("1.95"));
        invoice1.setTotal(new BigDecimal("213.84"));

        Invoice invoice2 = new Invoice();
        invoice2.setName("Karl Marx");
        invoice2.setStreet("5023 Opper Ave");
        invoice2.setCity("Boulder");
        invoice2.setState("Colorado");
        invoice2.setZipcode("69402");
        invoice2.setItemType("Game");
        invoice2.setItemId(1);
        invoice2.setQuantity(10);
        invoice2.setUnitPrice(new BigDecimal("19.99"));
        invoice2.setSubtotal(new BigDecimal("199.90"));
        invoice2.setTax(new BigDecimal("11.99"));
        invoice2.setProcessingFee(new BigDecimal("1.95"));
        invoice2.setTotal(new BigDecimal("213.84"));

        invoiceList.add(invoice1);
        invoiceList.add(invoice2);

        String inputJson = mapper.writeValueAsString(invoiceList);

        mockMvc.perform(
                        get("/invoices")
                                .content(inputJson)
                                .contentType(MediaType.APPLICATION_JSON))
                .andDo(print())
                .andExpect(status().isOk());
    }

    @Test
    public void shouldReturnOkStatusOnGetByName() throws Exception {

        Invoice invoice1 = new Invoice();
        invoice1.setName("Mark Jacobs");
        invoice1.setStreet("0593 Allen Ave");
        invoice1.setCity("San Diego");
        invoice1.setState("California");
        invoice1.setZipcode("94923");
        invoice1.setItemType("Game");
        invoice1.setItemId(1);
        invoice1.setQuantity(10);
        invoice1.setUnitPrice(new BigDecimal("19.99"));
        invoice1.setSubtotal(new BigDecimal("199.90"));
        invoice1.setTax(new BigDecimal("11.99"));
        invoice1.setProcessingFee(new BigDecimal("1.95"));
        invoice1.setTotal(new BigDecimal("213.84"));


        String inputJson = mapper.writeValueAsString(invoice1);

        mockMvc.perform(
                        get("/invoice/customer/Mark Jacobs")
                                .content(inputJson)
                                .contentType(MediaType.APPLICATION_JSON))
                .andDo(print())
                .andExpect(status().isOk());
    }

}
