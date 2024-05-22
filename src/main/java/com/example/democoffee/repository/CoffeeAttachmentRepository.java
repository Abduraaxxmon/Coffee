package com.example.democoffee.repository;

import com.example.democoffee.entity.Coffee;
import com.example.democoffee.entity.CoffeeAttachment;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface CoffeeAttachmentRepository extends JpaRepository<CoffeeAttachment,Long> {
    
}
