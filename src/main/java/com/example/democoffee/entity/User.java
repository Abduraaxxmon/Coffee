package com.example.democoffee.entity;

import jakarta.persistence.*;
import lombok.*;


import java.io.Serializable;
import java.time.LocalDate;
import java.util.Set;

@Entity
@Getter
@Setter
@Builder
@AllArgsConstructor
@NoArgsConstructor
@Table(name = "_users")
public class User implements Serializable {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "first_name",nullable = false)
    private String firstName;

    @Column(name = "last_name",nullable = false)
    private String lastname;

    @Column(name = "birthday",nullable = false)
    private LocalDate date;

//    @OneToMany(mappedBy = "userId")
//    private Set<Like> user;
//
    @OneToMany(mappedBy = "user")
    private Set<UserAttachment> attachments;

    @ManyToMany(cascade = CascadeType.ALL)
    @JoinTable(
            name = "likes",
            joinColumns = @JoinColumn(name = "user_id"),
            inverseJoinColumns = @JoinColumn(name = "coffee_id")
    )
    private Set<Coffee> likes;

    @OneToMany(mappedBy = "user",cascade = CascadeType.ALL)
    private Set<CardCount> cards;


}
