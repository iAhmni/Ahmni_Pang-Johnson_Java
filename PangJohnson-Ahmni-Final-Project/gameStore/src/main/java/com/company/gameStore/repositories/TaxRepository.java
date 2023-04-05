package com.company.gameStore.repositories;

import com.company.gameStore.models.Tax;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import java.util.List;

@Repository
public interface TaxRepository extends JpaRepository<Tax, Integer> {
    List<Tax> findByState(String state);
}
