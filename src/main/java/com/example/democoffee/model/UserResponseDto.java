package com.example.democoffee.model;

import com.example.democoffee.entity.Coffee;
import lombok.*;

import java.time.LocalDate;

@Getter
@Setter
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class UserResponseDto {
    private Long id;
    private String firstname;
    private String lastname;
    private LocalDate date;
    private String uri;
}
