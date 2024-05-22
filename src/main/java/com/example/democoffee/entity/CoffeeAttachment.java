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
@Table(name = "attachment_coffee")
public class CoffeeAttachment implements Serializable {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String name;
    private String path;
    private String contentType;
    private String extension;
    private Long size;


    @ManyToOne
    private Coffee coffee;
}
