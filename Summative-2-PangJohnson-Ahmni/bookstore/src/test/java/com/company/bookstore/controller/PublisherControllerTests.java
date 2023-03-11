package com.company.bookstore.controller;
import com.company.bookstore.Model.Author;
import com.company.bookstore.Model.Book;
import com.company.bookstore.Model.Publisher;
import com.company.bookstore.Repository.AuthorRepo;
import com.company.bookstore.Repository.BookRepo;
import com.company.bookstore.Repository.PublisherRepository;

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

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import java.util.HashSet;
import java.util.Optional;
import java.util.Set;

import static org.hamcrest.Matchers.hasSize;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

//@WebMvcTest(PublisherController.class)
@RunWith(SpringRunner.class)
@SpringBootTest
@AutoConfigureMockMvc
public class PublisherControllerTests {
    @Autowired
    private MockMvc mockMvc;
    @MockBean
    private PublisherRepository publisherRepo;
    @Autowired
    private BookRepo bookRepo;
    @Autowired
    private AuthorRepo authorRepo;
    @Before
    public void setUp() throws Exception {
        publisherRepo.deleteAll();
        bookRepo.deleteAll();
        authorRepo.deleteAll();
    }

    @PersistenceContext
    private EntityManager entityManager;
    private ObjectMapper mapper = new ObjectMapper();
    @Test
    public void shouldReturnNewPublisherOnValidPostRequest() throws Exception {
        Publisher publisher = new Publisher();
        publisher.setName("John Smith");
        publisher.setPhone("785");
        publisher.setStreet("dsa");
        publisher.setState("KS");
        publisher.setEmail("do@edu");
        publisher.setPostalCode(66617);
        publisher.setCity("Topeka");
        Book book = new Book();
        Author author = new Author();
        authorRepo.save(author);
        bookRepo.save(book);
        publisher = publisherRepo.save(publisher);
        book.setAuthor(author);
        book.setPublisher(publisher);
        Set<Book> books = new HashSet<Book>();
        books.add(book);
        publisher.setBooks(books);
        String inputJson = mapper.writeValueAsString(publisher);
        mockMvc.perform(MockMvcRequestBuilders
                        .post("/publishers")
                        .content(inputJson)
                        .contentType(MediaType.APPLICATION_JSON)
                )
                .andDo(print())
                .andExpect(status().isCreated());
    }

    @Test
    public void shouldDeleteByPublisherAndReturn204StatusCode() throws Exception {
        Publisher publisher = new Publisher();
        String inputJson = mapper.writeValueAsString(publisher);
        //Act...
        publisher = publisherRepo.save(publisher);

        //Assert...
        Optional<Publisher> publisher1 = publisherRepo.findById(publisher.getId());

        mockMvc.perform(MockMvcRequestBuilders
                        .delete("/publishers/{id}", publisher.getId()))
                .andDo(print())
                .andExpect(status().isNoContent());
    }

    @Test
    public void shouldUpdatePublisher() throws Exception {
        Publisher publisher = new Publisher();
        String inputJson = mapper.writeValueAsString(publisher);

        mockMvc.perform(MockMvcRequestBuilders
                        .put("/publishers/")
                        .content(inputJson)
                        .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isNoContent());
    }

    @Test
    public void shouldReturnAllPublishers() throws Exception {
        Publisher publisher = new Publisher();
        publisher.setId(1);
        publisher.setName("John Smith");

        String inputJson = mapper.writeValueAsString(publisher);

        Publisher publisher2 = new Publisher();
        publisher2.setName("Bob Marley");
        publisher2 = publisherRepo.save(publisher2);
        //Act...
        publisher = publisherRepo.save(publisher);

        mockMvc.perform(get("/publishers/"))
                .andDo(print())
                .andExpect(status().isOk())
                .andExpect(jsonPath("$", hasSize(2)));
    }

    @Test
    public void shouldReturnPublisherByID() throws Exception {
        Publisher publisher = new Publisher();

        //Act...
        publisher = publisherRepo.save(publisher);

        //Assert...
        Optional<Publisher> publisher1 = publisherRepo.findById(publisher.getId());
        String inputJson = mapper.writeValueAsString(publisher);
        mockMvc.perform(get("/publishers/{id}", publisher.getId()))
                .andDo(print())
                .andExpect(status().isOk())
                .andExpect(content().json(inputJson));
    }
}
