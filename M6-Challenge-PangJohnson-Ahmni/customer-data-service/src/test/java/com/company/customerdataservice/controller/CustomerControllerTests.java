package com.company.customerdataservice.controller;
import com.company.customerdataservice.model.Customer;
import com.company.customerdataservice.repository.CustomerRepository;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

@RunWith(SpringRunner.class)
@WebMvcTest(CustomerController.class)
public class CustomerControllerTests {
    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private CustomerRepository customerRepo;

    @Before
    public void setUp() throws Exception {
        customerRepo.deleteAll();
    }
    private ObjectMapper mapper = new ObjectMapper();
    @Test
    public void shouldReturnNewCustomerOnValidPostRequest() throws Exception {
        Customer customer = new Customer();
        customer.setFirstName("Joe");
        customer.setLastName("Smith");
        customer.setPhone("111-222-3456");
        customer.setCompany("BigCo");
        customer.setAddress1("456 Timberly Lane");
        customer.setAddress2("123 Fake St");
        customer.setCity("Topeka");
        customer.setState("KS");
        customer.setPostalCode(60);
        customer.setCountry("United States");

        String inputJson = mapper.writeValueAsString(customer);
        String outputJson = mapper.writeValueAsString(customer);
        customerRepo.save(customer);
        mockMvc.perform(MockMvcRequestBuilders
                        .post("/customers")
                                .content(inputJson)
                                .contentType(MediaType.APPLICATION_JSON)
                )
                .andDo(print())
                .andExpect(status().isCreated());
    }

    @Test
    public void shouldDeleteByCustomerAndReturn204StatusCode() throws Exception {
        Customer customer = new Customer();
        customer.setId(1);
        String inputJson = mapper.writeValueAsString(customer);
        mockMvc.perform(
                post("/customers")
                        .content(inputJson)
                        .contentType(MediaType.APPLICATION_JSON));

        mockMvc.perform(MockMvcRequestBuilders
                        .delete("/customers/1"))
                .andDo(print())
                .andExpect(status().isNoContent());
    }

    @Test
    public void shouldUpdateCustomer() throws Exception {
        Customer customer = new Customer();
        customer.setId(1);
        String inputJson = mapper.writeValueAsString(customer);
        mockMvc.perform(
                post("/customers")
                        .content(inputJson)
                        .contentType(MediaType.APPLICATION_JSON));
        customer.setFirstName("Updated");
        inputJson = mapper.writeValueAsString(customer);
        mockMvc.perform(MockMvcRequestBuilders
                        .put("/customers/")
                        .content(inputJson)
                        .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isNoContent());
    }

    @Test
    public void shouldReturnAllCustomersByState() throws Exception {
        Customer customer = new Customer();
        customer.setId(1);
        customer.setFirstName("Joe");
        customer.setLastName("Smith");
        customer.setPhone("111-222-3456");
        customer.setCompany("BigCo");
        customer.setAddress1("456 Timberly Lane");
        customer.setAddress2("123 Fake St");
        customer.setCity("Topeka");
        customer.setState("KS");
        customer.setPostalCode(60);
        customer.setCountry("United States");
        String inputJson = mapper.writeValueAsString(customer);
        mockMvc.perform(
                post("/customers")
                        .content(inputJson)
                        .contentType(MediaType.APPLICATION_JSON));
        mockMvc.perform(get("/customers/state/KS"))
                .andDo(print())
                .andExpect(status().isOk());
    }

    @Test
    public void shouldReturnCustomerByID() throws Exception {
        Customer customer = new Customer();
        customer.setId(1);
        String inputJson = mapper.writeValueAsString(customer);
        mockMvc.perform(
                post("/customers")
                        .content(inputJson)
                        .contentType(MediaType.APPLICATION_JSON));
        mockMvc.perform(get("/customers/1"))
                .andDo(print())
                .andExpect(status().isOk());
    }
}
