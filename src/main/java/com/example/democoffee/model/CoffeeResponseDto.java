package com.example.democoffee.model;

import lombok.*;

@Getter
@Setter
@Builder
@AllArgsConstructor
@NoArgsConstructor

public class CoffeeResponseDto {
    private Long id ;
    private String name;
    private String description;
    private Double cost;
    private CategoryResponseDto category;
}
