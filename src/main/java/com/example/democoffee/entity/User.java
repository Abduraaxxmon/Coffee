package com.example.democoffee.entity;

import jakarta.persistence.*;
import lombok.*;
import org.hibernate.property.access.internal.PropertyAccessStrategyNoopImpl;
import org.springframework.stereotype.Component;

import java.time.LocalDate;
import java.util.Date;
import java.util.Set;

@Entity
@Getter
@Setter
@Builder
@AllArgsConstructor
@NoArgsConstructor
@Table(name = "_users")
public class User {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "first_name",nullable = false)
    private String firstName;

    @Column(name = "last_name",nullable = false)
    private String lastname;

    @Column(name = "birthday",nullable = false)
    private LocalDate date;

    @OneToMany(mappedBy = "user")
    private Set<Like> like;

}
