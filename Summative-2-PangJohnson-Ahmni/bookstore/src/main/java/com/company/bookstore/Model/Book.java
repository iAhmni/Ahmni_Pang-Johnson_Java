package com.company.bookstore.Model;
import com.fasterxml.jackson.annotation.JsonManagedReference;

import javax.persistence.*;
import java.math.BigDecimal;
import java.time.LocalDate;


@Entity
@Table(name="book")
public class Book {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name="book_id")
    private Integer id;

    @Column(name = "isbn", nullable = false)
    private String isbn;

    public LocalDate getPublishDate() {
        return publishDate;
    }

    public void setPublishDate(LocalDate publishDate) {
        this.publishDate = publishDate;
    }

    @Column(name = "publish_date", nullable = false)
    private LocalDate publishDate;

    public Author getAuthor() {
        return author;
    }

    public void setAuthor(Author author) {
        this.author = author;
    }

    @ManyToOne
    @JoinColumn(name = "author_id", nullable = false)
    private Author author;

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    @Column(name = "title", nullable = false)
    private String title;

    public Publisher getPublisher() {
        return publisher;
    }

    public void setPublisher(Publisher publisher) {
        this.publisher = publisher;
    }

    @ManyToOne
    @JsonManagedReference
    @JoinColumn(name = "publisher_id", nullable = false)
    private Publisher publisher;

    public BigDecimal getPrice() {
        return price;
    }

    public void setPrice(BigDecimal price) {
        this.price = price;
    }

    @Column(name = "price", nullable = false)
    private BigDecimal price;

    public Book() {
    }

    public Book(String isbn, LocalDate publishDate, Author author, String title, Publisher publisher, BigDecimal price) {
        this.isbn = isbn;
        this.publishDate = publishDate;
        this.author = author;
        this.title = title;
        this.publisher = publisher;
        this.price = price;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getIsbn() {
        return isbn;
    }

    public void setIsbn(String isbn) {
        this.isbn = isbn;
    }

}
