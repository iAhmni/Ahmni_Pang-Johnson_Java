package com.company.gameStore.repositories;

import com.company.gameStore.models.TShirt;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface TShirtRepository extends JpaRepository<TShirt, Integer> {

    List<TShirt> findByColor(String color);
    List<TShirt> findBySize(String size);

}
