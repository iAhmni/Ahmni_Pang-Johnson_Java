package com.company.bookstore.repository;

import com.company.bookstore.Model.Author;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

import static org.assertj.core.api.Assertions.assertThat;

@DataJpaTest
public class AuthorRepoTest {

    @PersistenceContext
    private EntityManager entityManager;

    @Test
    public void testSaveAndFindById() {
        // Arrange
        Author author = new Author("John", "Doe", "123 Main St", "Anytown", "NY", "12345", "555-555-1212", "johndoe@example.com");

        // Act
        entityManager.persist(author);
        entityManager.flush();

        Author foundAuthor = entityManager.find(Author.class, author.getAuthorId());

        // Assert
        assertThat(foundAuthor).isEqualTo(author);
    }

    @Test
    public void testUpdate() {
        // Arrange
        Author author = new Author("John", "Doe", "123 Main St", "Anytown", "NY", "12345", "555-555-1212", "johndoe@example.com");
        entityManager.persist(author);
        entityManager.flush();

        // Act
        author.setEmail("jdoe@example.com");
        entityManager.flush();

        Author updatedAuthor = entityManager.find(Author.class, author.getAuthorId());

        // Assert
        assertThat(updatedAuthor.getEmail()).isEqualTo("jdoe@example.com");
    }

    @Test
    public void testDelete() {
        // Arrange
        Author author = new Author("John", "Doe", "123 Main St", "Anytown", "NY", "12345", "555-555-1212", "johndoe@example.com");
        entityManager.persist(author);
        entityManager.flush();

        // Act
        entityManager.remove(author);
        entityManager.flush();

        Author deletedAuthor = entityManager.find(Author.class, author.getAuthorId());

        // Assert
        assertThat(deletedAuthor).isNull();
    }
}

