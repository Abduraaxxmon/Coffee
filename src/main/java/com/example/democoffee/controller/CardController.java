package com.example.democoffee.controller;

import com.example.democoffee.service.CardCountService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;


//Long getCardCount(Long userId,Long coffeeId);
//void addCard(Long userId,Long coffeeId);
//void minusCard(Long userId,Long coffeeId);
@RestController
@RequestMapping("/api/v1/card")
@RequiredArgsConstructor
public class CardController {
    private final CardCountService service;

    @GetMapping("getCards")
    public ResponseEntity<?> getCardCount(@RequestParam Long userId, @RequestParam Long coffeeId) {
        return ResponseEntity.ok(service.getCardCount(userId, coffeeId));
    }

    @PostMapping("/add")
    public ResponseEntity<?> addCard(@RequestParam Long userId, @RequestParam Long coffeeId) {
        service.addCard(userId, coffeeId);
        return ResponseEntity.ok().build();
    }

    @DeleteMapping("/delete")
    public ResponseEntity<?> deleteCard(@RequestParam Long userId, @RequestParam Long coffeeId) {
        service.minusCard(userId, coffeeId);
        return ResponseEntity.ok().build();
    }
}
