package com.example.democoffee.entity;

import jakarta.persistence.Embeddable;
import lombok.*;

import java.io.Serializable;
@Embeddable
@Getter
@Setter
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class UserLikeKey implements Serializable {
    private Long userId;
    private Long coffeeId;
}
