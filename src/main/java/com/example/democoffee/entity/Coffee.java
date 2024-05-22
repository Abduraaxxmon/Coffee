package com.example.democoffee.entity;

import jakarta.persistence.*;
import lombok.*;

import java.io.Serializable;
import java.util.Set;

@Entity
@Getter
@Setter
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class Coffee implements Serializable {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "name",nullable = false)
    private String name;

    @Column(name = "description",nullable = false)
    private String description;

    @Column(name = "cost", nullable = false)
    private Double cost;

    @Column(name = "rate",nullable = false)
    private Double rate;

    @Column(name = "size",nullable = false)
    @Enumerated(EnumType.STRING)
    private Size size;


    @ManyToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "category_id",referencedColumnName = "id")
    private Category category;

    @OneToMany(mappedBy = "coffee", cascade = CascadeType.ALL)
    private Set<CoffeeAttachment> attachments;

    @ManyToMany(mappedBy = "likes")
    private Set<User> likedByUser;

    @OneToMany(mappedBy = "id")
    private Set<User> users;

    @OneToMany(mappedBy = "coffee",cascade = CascadeType.ALL)
    private Set<CardCount> coffees;
}
