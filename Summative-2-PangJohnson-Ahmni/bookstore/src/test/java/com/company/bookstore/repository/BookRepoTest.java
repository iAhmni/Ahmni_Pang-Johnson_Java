package com.company.bookstore.repository;

import com.company.bookstore.Model.Author;
import com.company.bookstore.Model.Book;
import com.company.bookstore.Model.Publisher;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

import java.math.BigDecimal;
import java.time.LocalDate;

import static org.junit.jupiter.api.Assertions.assertEquals;

@SpringBootTest
public class BookRepoTest {

    @Test
    public void testBookConstructorAndGetters() {
        // Create test data for Book object
        String isbn = "9783161484100";
        LocalDate publishDate = LocalDate.of(2021, 1, 1);
        Author author = new Author("John", "Doe", "123 Main St", "Anytown", "NY", "12345", "555-555-1212", "johndoe@example.com");
        String title = "Test Book";
        Publisher publisher1 = new Publisher();
        publisher1.setId(1);
        publisher1.setName("John");
        publisher1.setStreet("123 Main St");
        publisher1.setCity("Anytown");
        publisher1.setState("CA");
        publisher1.setPostalCode(12345);
        publisher1.setEmail("john.doe@example.com");
        BigDecimal price = new BigDecimal("19.99");

        // Create Book object using constructor
        Book book = new Book(isbn, publishDate, author, title, publisher1, price);

        // Verify values using getters
        assertEquals(isbn, book.getIsbn());
        assertEquals(publishDate, book.getPublishDate());
        assertEquals(author, book.getAuthor());
        assertEquals(title, book.getTitle());
        assertEquals(price, book.getPrice());
    }
}
