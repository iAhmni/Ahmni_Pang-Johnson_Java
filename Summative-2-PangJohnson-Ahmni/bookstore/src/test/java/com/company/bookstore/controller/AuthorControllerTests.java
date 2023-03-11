package com.company.bookstore.controller;

import com.company.bookstore.Model.Publisher;
import com.company.bookstore.Repository.PublisherRepository;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.company.bookstore.Model.Author;
import com.company.bookstore.Model.Book;
import com.company.bookstore.Model.Publisher;
import com.company.bookstore.Repository.AuthorRepo;
import com.company.bookstore.Repository.BookRepo;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;

import java.util.HashSet;
import java.util.Optional;
import java.util.Set;

import static org.hamcrest.Matchers.hasSize;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

@RunWith(SpringRunner.class)
@SpringBootTest
@AutoConfigureMockMvc
public class AuthorControllerTests {
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
        authorRepo.deleteAll();
        authorRepo.deleteAll();
    }
    private ObjectMapper mapper = new ObjectMapper();

    @Test
    public void shouldReturnNewAuthorOnValidPostRequest() throws Exception {
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
        authorRepo.save(author);
        publisherRepo.save(publisher);
        book.setAuthor(author);
        book.setPublisher(publisher);
        Set<Book> books = new HashSet<Book>();
        books.add(book);
        publisher.setBooks(books);
        String inputJson = mapper.writeValueAsString(author);
        mockMvc.perform(MockMvcRequestBuilders
                        .post("/authors")
                        .content(inputJson)
                        .contentType(MediaType.APPLICATION_JSON)
                )
                .andDo(print())
                .andExpect(status().isCreated());
    }

    @Test
    public void shouldDeleteByAuthorAndReturn204StatusCode() throws Exception {
        Author author = new Author();
        String inputJson = mapper.writeValueAsString(author);
        //Act...
        author = authorRepo.save(author);

        //Assert...
        Optional<Author> author1 = authorRepo.findById(author.getAuthorId());

        mockMvc.perform(MockMvcRequestBuilders
                        .delete("/authors/{id}", author.getAuthorId()))
                .andDo(print())
                .andExpect(status().isNoContent());
    }

    @Test
    public void shouldUpdateAuthor() throws Exception {
        Author author = new Author();
        String inputJson = mapper.writeValueAsString(author);

        mockMvc.perform(MockMvcRequestBuilders
                        .put("/authors/")
                        .content(inputJson)
                        .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isNoContent());
    }

    @Test
    public void shouldReturnAllAuthors() throws Exception {
        Author author = new Author();
        author.setFirstName("Author1");

        String inputJson = mapper.writeValueAsString(author);

        Author author2 = new Author();
        author2.setFirstName("Author2");
        author2 = authorRepo.save(author2);
        //Act...
        author = authorRepo.save(author);

        mockMvc.perform(get("/authors/"))
                .andDo(print())
                .andExpect(status().isOk())
                .andExpect(jsonPath("$", hasSize(2)));
    }

    @Test
    public void shouldReturnAuthorByID() throws Exception {
        Author author = new Author();

        //Act...
        author = authorRepo.save(author);

        //Assert...
        Optional<Author> author1 = authorRepo.findById(author.getAuthorId());
        String inputJson = mapper.writeValueAsString(author);
        mockMvc.perform(get("/authors/{id}", author.getAuthorId()))
                .andDo(print())
                .andExpect(status().isOk())
                .andExpect(content().json(inputJson));
    }
}
