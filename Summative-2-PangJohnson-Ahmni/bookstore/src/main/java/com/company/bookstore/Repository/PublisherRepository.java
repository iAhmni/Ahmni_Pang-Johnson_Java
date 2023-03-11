package com.company.bookstore.Repository;

import com.company.bookstore.Model.Publisher;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.ArrayList;
import java.util.List;

@Repository
public interface PublisherRepository extends JpaRepository<Publisher, Integer> {
}
