package com.example.democoffee.model;

import jakarta.persistence.NamedStoredProcedureQueries;
import lombok.*;

@Getter
@Setter
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class CategoryResponseDto {
    private Long id;
    private String name;
}
