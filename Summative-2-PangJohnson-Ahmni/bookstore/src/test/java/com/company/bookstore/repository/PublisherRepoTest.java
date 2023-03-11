package com.company.bookstore.repository;

import com.company.bookstore.Model.Author;
import com.company.bookstore.Model.Book;
import com.company.bookstore.Model.Publisher;
import com.company.bookstore.Repository.AuthorRepo;
import com.company.bookstore.Repository.BookRepo;
import com.company.bookstore.Repository.PublisherRepository;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.HashSet;
import java.util.List;
import java.util.Optional;
import java.util.Set;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;

@RunWith(SpringRunner.class)
@SpringBootTest
public class PublisherRepoTest {
    @Autowired
    PublisherRepository publisherRepo;


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

    @Test
    public void addPublisher() {
        //Arrange...
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
        book.setAuthor(author);
        book.setPublisher(publisher);
        bookRepo.save(book);
        Set<Book> books = new HashSet<Book>();
        books.add(book);
        publisher.setBooks(books);
        publisherRepo.save(publisher);


        //Assert...
        Optional<Publisher> publisher1 = publisherRepo.findById(publisher.getId());

        assertEquals(publisher1.get(), publisher);
    }
    @Test
    public void getPublisher() {
        //Arrange...
        Publisher publisher = new Publisher();
        publisher.setName("John Smith");
        publisher.setPhone("785");
        publisher.setStreet("dsa");
        publisher.setState("KS");
        publisher.setEmail("do@edu");
        publisher.setPostalCode(66617);
        publisher.setCity("Topeka");


        Publisher publisher2 = new Publisher();
        publisher.setName("Bob Marley");
        publisher.setPhone("785");
        publisher.setStreet("dsa");
        publisher.setState("KS");
        publisher.setEmail("do@edu");
        publisher.setPostalCode(66617);
        publisher.setCity("Topeka");

        publisherRepo.save(publisher2);
        //Act...
        publisher = publisherRepo.save(publisher);

        //Assert...
        Optional<Publisher> publisher1 = publisherRepo.findById(publisher.getId());

        assertEquals(publisher1.get(), publisher);
    }
    @Test
    public void getPublishersByState() {
        //Arrange...

        //Act...
        Publisher publisher = new Publisher();
        publisher.setName("John Smith");
        publisher.setPhone("785");
        publisher.setStreet("dsa");
        publisher.setState("KS");
        publisher.setEmail("do@edu");
        publisher.setPostalCode(66617);
        publisher.setCity("Topeka");


        publisherRepo.save(publisher);

        Publisher publisher2 = new Publisher();
        publisher.setName("Bob Marley");
        publisher.setPhone("785");
        publisher.setStreet("dsa");
        publisher.setState("KS");
        publisher.setEmail("do@edu");
        publisher.setPostalCode(66617);
        publisher.setCity("Topeka");

        publisherRepo.save(publisher2);

        List<Publisher> publisherList = publisherRepo.findAll();

        //Assert...
        assertEquals(2, publisherList.size());
    }

    @Test
    public void updatePublisher() {
        //Arrange...
        Publisher publisher = new Publisher();
        publisher.setName("John Smith");
        publisher.setPhone("785");
        publisher.setStreet("dsa");
        publisher.setState("KS");
        publisher.setEmail("do@edu");
        publisher.setPostalCode(66617);
        publisher.setCity("Topeka");

        publisherRepo.save(publisher);

        //Act...
        publisher.setName("UPDATED");

        publisherRepo.save(publisher);

        //Assert...
        Optional<Publisher> publisher1 = publisherRepo.findById(publisher.getId());

        assertEquals(publisher1.get(), publisher);
    }

    @Test
    public void deletePublisher() {
        //Arrange...
        Publisher publisher = new Publisher();
        publisher.setName("John Smith");
        publisher.setPhone("785");
        publisher.setStreet("dsa");
        publisher.setState("KS");
        publisher.setEmail("do@edu");
        publisher.setPostalCode(66617);
        publisher.setCity("Topeka");

        publisherRepo.save(publisher);

        //Act...
        publisherRepo.deleteById(publisher.getId());

        //Assert...
        Optional<Publisher> publisher1 = publisherRepo.findById(publisher.getId());
        assertFalse(publisher1.isPresent());
    }
}