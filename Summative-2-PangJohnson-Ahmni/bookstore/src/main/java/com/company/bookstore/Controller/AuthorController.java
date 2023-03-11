package com.company.bookstore.Controller;

import com.company.bookstore.Model.Author;
import com.company.bookstore.Repository.AuthorRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Optional;

@RestController
@RequestMapping("/author")
public class AuthorController {

    @Autowired
    private AuthorRepo authorRepo;

    @PostMapping
    public ResponseEntity<Author> createAuthor(Author author) {
        Author newAuthor = authorRepo.save(author);
        return ResponseEntity.ok(newAuthor);
    }

    @GetMapping("/author/{authorid}")
    public ResponseEntity<Author> getAuthorById(@PathVariable Integer id) {
        Optional<Author> author = authorRepo.findById(id);
        return author.map(ResponseEntity::ok).orElseGet(() -> ResponseEntity.notFound().build());
    }
    
    @GetMapping
    public ResponseEntity<Iterable<Author>> getAllAuthors() {
        Iterable<Author> authors = authorRepo.findAll();
        return ResponseEntity.ok(authors);
    }
    
    @PutMapping("/author/{authorid}")
    public ResponseEntity<Author> updateAuthor(@PathVariable Integer id, Author author) {
        Optional<Author> authorOptional = authorRepo.findById(id);
        if (authorOptional.isPresent()) {
            Author existingAuthor = authorOptional.get();
            existingAuthor.setFirstName(author.getFirstName());
            existingAuthor.setLastName(author.getLastName());
            existingAuthor.setStreet(author.getStreet());
            existingAuthor.setCity(author.getCity());
            existingAuthor.setState(author.getState());
            existingAuthor.setPostalCode(author.getPostalCode());
            existingAuthor.setPhone(author.getPhone());
            existingAuthor.setEmail(author.getEmail());
            authorRepo.save(existingAuthor);
            
            return  new ResponseEntity<>(existingAuthor, HttpStatus.OK);
        }
        return ResponseEntity.notFound().build();
    }

    @DeleteMapping("/author/{authorid}")
    public ResponseEntity<Author> deleteAuthor(@PathVariable("id") Integer id) {
        Optional<Author> bookOptional = authorRepo.findById(id);
        if (bookOptional.isPresent()) {
            authorRepo.deleteById(id);
            return new ResponseEntity<>(HttpStatus.OK);
        }
        return new ResponseEntity<>(HttpStatus.NOT_FOUND);
    }
}
