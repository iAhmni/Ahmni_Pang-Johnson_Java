package com.company.bookstore.Controller;


import com.company.bookstore.Model.Book;
import com.company.bookstore.Repository.BookRepo;
import org.apache.coyote.Response;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Optional;

@RestController
@RequestMapping("/books")
public class BookController {
    @Autowired
    private BookRepo bookRepo;

    @PostMapping
    public ResponseEntity<Book> createBook(Book book) {
        Book newBook = bookRepo.save(book);
        return ResponseEntity.ok(newBook);
    }

    @GetMapping("/books/{id}")
    public ResponseEntity<Book> getBookById(@PathVariable("id") Integer id) {
        Optional<Book> bookOptional = bookRepo.findById(id);
        if (bookOptional.isPresent()) {
            Book book = bookOptional.get();
            return new ResponseEntity<>(book, HttpStatus.OK);
        }
        return new ResponseEntity<>(HttpStatus.NOT_FOUND);
    }

    @GetMapping
    public ResponseEntity<Iterable<Book>> getAllBooks() {
        Iterable<Book> books = bookRepo.findAll();
        return new ResponseEntity<>(books, HttpStatus.OK);
    }

    @PutMapping("/books/{id}")
    public ResponseEntity<Book> updateBook(@PathVariable("id") Integer id, Book book) {
        Optional<Book> bookOptional = bookRepo.findById(id);
        if (bookOptional.isPresent()) {
            Book bookToUpdate = bookOptional.get();
            bookToUpdate.setAuthor(book.getAuthor());
            bookToUpdate.setIsbn(book.getIsbn());
            bookToUpdate.setPublisher(book.getPublisher());
            bookToUpdate.setPublishDate(book.getPublishDate());
            bookToUpdate.setTitle(book.getTitle());
            bookRepo.save(bookToUpdate);
            return new ResponseEntity<>(bookToUpdate, HttpStatus.OK);
        }
        return new ResponseEntity<>(HttpStatus.NOT_FOUND);
    }

    @DeleteMapping("/books/{id}")
    public ResponseEntity<Book> deleteBook(@PathVariable("id") Integer id) {
        Optional<Book> bookOptional = bookRepo.findById(id);
        if (bookOptional.isPresent()) {
            bookRepo.deleteById(id);
            return new ResponseEntity<>(HttpStatus.OK);
        }
        return new ResponseEntity<>(HttpStatus.NOT_FOUND);
    }

    @GetMapping("/author/{authorId}")
    public ResponseEntity<Iterable<Book>> getBooksByAuthor(@PathVariable("authorId") Integer authorId) {
        Iterable<Book> books = bookRepo.findByAuthorId(authorId);
        return new ResponseEntity<>(books, HttpStatus.OK);
    }
}
