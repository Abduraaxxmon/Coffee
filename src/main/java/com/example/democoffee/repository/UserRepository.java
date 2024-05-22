package com.example.democoffee.repository;

import com.example.democoffee.entity.CardCount;
import com.example.democoffee.entity.Coffee;
import com.example.democoffee.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.RepositoryDefinition;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface UserRepository extends JpaRepository<User,Long> {
    @Query("SELECT c FROM Coffee c JOIN c.likedByUser u WHERE u.id = :userId")
    List<Coffee> findCoffeesLikedByUser(@Param("userId") Long userId);
}
