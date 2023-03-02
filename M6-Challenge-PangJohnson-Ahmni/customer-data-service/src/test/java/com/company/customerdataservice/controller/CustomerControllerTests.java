package com.company.customerdataservice.controller;
import com.company.customerdataservice.model.Customer;
import com.company.customerdataservice.repository.CustomerRepository;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;

import java.util.Optional;

import static org.hamcrest.Matchers.hasSize;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

//@WebMvcTest(CustomerController.class)
@RunWith(SpringRunner.class)
@SpringBootTest
@AutoConfigureMockMvc
public class CustomerControllerTests {
    @Autowired
    private MockMvc mockMvc;

    @Autowired
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
        String inputJson = mapper.writeValueAsString(customer);
        //Act...
        customer = customerRepo.save(customer);

        //Assert...
        Optional<Customer> customer1 = customerRepo.findById(customer.getId());

        mockMvc.perform(MockMvcRequestBuilders
                        .delete("/customers/{id}", customer.getId()))
                .andDo(print())
                .andExpect(status().isNoContent());
    }

    @Test
    public void shouldUpdateCustomer() throws Exception {
        Customer customer = new Customer();
        String inputJson = mapper.writeValueAsString(customer);

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

        Customer customer2 = new Customer();
        customer2.setFirstName("Bob");
        customer2.setLastName("Marley");
        customer2.setPhone("222-333-4567");
        customer2.setCompany("Independent");
        customer2.setAddress1("456 Timberly Lane");
        customer2.setAddress2("123 Fake St");
        customer2.setCity("Lawrence");
        customer2.setState("KS");
        customer2.setPostalCode(63);
        customer2.setCountry("United States");
        customer2 = customerRepo.save(customer2);
        //Act...
        customer = customerRepo.save(customer);

        mockMvc.perform(get("/customers/state/KS"))
                .andDo(print())
                .andExpect(status().isOk())
                .andExpect(jsonPath("$", hasSize(2)));
    }

    @Test
    public void shouldReturnCustomerByID() throws Exception {
        Customer customer = new Customer();

        //Act...
        customer = customerRepo.save(customer);

        //Assert...
        Optional<Customer> customer1 = customerRepo.findById(customer.getId());
        String inputJson = mapper.writeValueAsString(customer);
        mockMvc.perform(get("/customers/{id}", customer.getId()))
                .andDo(print())
                .andExpect(status().isOk())
                .andExpect(content().json(inputJson));
    }
}
