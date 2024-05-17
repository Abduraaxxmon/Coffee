package com.example.democoffee.repository;

import com.example.democoffee.entity.User;
import com.example.democoffee.entity.UserAttachment;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface UserAttachmentRepository extends JpaRepository<UserAttachment,Long> {
    User findByUserId(Long userId);
}
