package com.example.democoffee.entity;

import jakarta.persistence.*;
import lombok.*;

import java.io.Serializable;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
@Entity
@Table(name ="attachment_user")
public class UserAttachment implements Serializable {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)

    @Column(nullable = false)
    private Long id;

    @Column(nullable = false)
    private String name;

    @Column(nullable = false)
    private String path;

    @Column(nullable = false)
    private String contentType;

    @Column(nullable = false)
    private String extension;

    @Column(nullable = false)
    private Long size;


    @ManyToOne(cascade = CascadeType.ALL)
    private User user;
}
