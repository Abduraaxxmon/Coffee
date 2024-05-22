package com.example.democoffee.model;

import lombok.*;

import java.time.LocalDate;

@Getter
@Setter
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class UserRequestDto {
    private String firstname;
    private String lastname;
    private LocalDate date;

}
