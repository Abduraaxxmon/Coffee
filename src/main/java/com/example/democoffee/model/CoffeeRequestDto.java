package com.example.democoffee.model;

import com.example.democoffee.entity.Size;
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
    private Double rate;
    private Size coffeeSize;
    private CategoryResponseDto category;

}
