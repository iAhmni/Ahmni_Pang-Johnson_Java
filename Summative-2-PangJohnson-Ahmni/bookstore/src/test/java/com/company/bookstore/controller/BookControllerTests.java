package com.company.bookstore.controller;

import com.company.bookstore.Model.Publisher;
import com.company.bookstore.Repository.PublisherRepository;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.company.bookstore.Model.Author;
import com.company.bookstore.Model.Book;
import com.company.bookstore.Model.Book;
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
public class BookControllerTests {
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
    private ObjectMapper mapper = new ObjectMapper();

    @Test
    public void shouldReturnNewBookOnValidPostRequest() throws Exception {
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
        publisherRepo.save(publisher);
        book.setAuthor(author);
        book.setPublisher(publisher);
        Set<Book> books = new HashSet<Book>();
        books.add(book);
        publisher.setBooks(books);
        String inputJson = mapper.writeValueAsString(book);
        mockMvc.perform(MockMvcRequestBuilders
                        .post("/books")
                        .content(inputJson)
                        .contentType(MediaType.APPLICATION_JSON)
                )
                .andDo(print())
                .andExpect(status().isCreated());
    }

    @Test
    public void shouldDeleteByBookAndReturn204StatusCode() throws Exception {
        Book book = new Book();
        String inputJson = mapper.writeValueAsString(book);
        //Act...
        book = bookRepo.save(book);

        //Assert...
        Optional<Book> book1 = bookRepo.findById(book.getId());

        mockMvc.perform(MockMvcRequestBuilders
                        .delete("/books/{id}", book.getId()))
                .andDo(print())
                .andExpect(status().isNoContent());
    }

    @Test
    public void shouldUpdateBook() throws Exception {
        Book book = new Book();
        String inputJson = mapper.writeValueAsString(book);

        mockMvc.perform(MockMvcRequestBuilders
                        .put("/books/")
                        .content(inputJson)
                        .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isNoContent());
    }

    @Test
    public void shouldReturnAllBooks() throws Exception {
        Book book = new Book();
        book.setId(1);
        book.setTitle("Book1");

        String inputJson = mapper.writeValueAsString(book);

        Book book2 = new Book();
        book2.setTitle("Book2");
        book2 = bookRepo.save(book2);
        //Act...
        book = bookRepo.save(book);

        mockMvc.perform(get("/books/"))
                .andDo(print())
                .andExpect(status().isOk())
                .andExpect(jsonPath("$", hasSize(2)));
    }

    @Test
    public void shouldReturnBookByID() throws Exception {
        Book book = new Book();

        //Act...
        book = bookRepo.save(book);

        //Assert...
        Optional<Book> book1 = bookRepo.findById(book.getId());
        String inputJson = mapper.writeValueAsString(book);
        mockMvc.perform(get("/books/{id}", book.getId()))
                .andDo(print())
                .andExpect(status().isOk())
                .andExpect(content().json(inputJson));
    }
}
