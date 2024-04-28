package com.example.democoffee.model;

import lombok.*;

import java.time.LocalDate;

@Getter
@Setter
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class CoffeeRequestDto {
    private String name;
    private String description;
    private Double cost;
    private CategoryResponseDto category;
}
