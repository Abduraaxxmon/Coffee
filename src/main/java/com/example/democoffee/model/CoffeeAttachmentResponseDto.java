package com.example.democoffee.model;

import com.example.democoffee.entity.Size;
import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.*;

@Getter
@Setter
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class CoffeeAttachmentResponseDto {
    private Long id ;
    private String name;
    private String description;
    private Double cost;
    private String uri;
    private Double rate;
    private Long card;
    private Long pictureSize;
    private Size coffeeSize;
}
