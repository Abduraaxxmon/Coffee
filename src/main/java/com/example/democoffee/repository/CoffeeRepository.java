package com.example.democoffee.repository;

import com.example.democoffee.entity.Coffee;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface CoffeeRepository extends JpaRepository<Coffee,Long> {
    List<Coffee> findAllByCategoryId(Long categoryId);
    Coffee findByName(String name);
}
