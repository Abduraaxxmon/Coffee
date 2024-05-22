package com.example.democoffee.repository;

import com.example.democoffee.entity.CardCount;
import com.example.democoffee.entity.Coffee;
import com.example.democoffee.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface CardCountRepository extends JpaRepository<CardCount,Long> {
    CardCount findCardCountByUserAndCoffee(User user, Coffee coffee);
}
