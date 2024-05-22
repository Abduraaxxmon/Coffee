package com.example.democoffee.controller;

import com.example.democoffee.model.CoffeeAttachmentResponseDto;
import com.example.democoffee.model.UserRequestDto;
import com.example.democoffee.service.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("api/v1/user")
@RequiredArgsConstructor
public class UserController {
    private final UserService service;

    @PostMapping("/create")
    public ResponseEntity<?> create(@RequestBody UserRequestDto dto) {
        return ResponseEntity.ok(service.create(dto));
    }

    @GetMapping("/read")
    public ResponseEntity<?> getById(@RequestParam Long id) {
        return ResponseEntity.ok(service.read(id));
    }

    @GetMapping("/get-user-liked-coffee")
    ResponseEntity<List<CoffeeAttachmentResponseDto>> getUserLikedAttachments(@RequestParam Long userId) {
        return ResponseEntity.ok(service.readUserLikedCoffee(userId));
    }

    @GetMapping("/readAll")
    public ResponseEntity<?> getAll() {
        return ResponseEntity.ok(service.readAll());
    }
    @PutMapping("/update/user-liked-coffee")
    public ResponseEntity<?> updateUserLikedCoffee(@RequestParam Long userId, @RequestParam Long coffeeId) {
        service.updateUserLikedCoffee(coffeeId,userId);
        return ResponseEntity.ok().build();
    }

    @PutMapping("/update")
    public ResponseEntity<?> updateById(@RequestParam Long id, @RequestBody UserRequestDto dto) {
        return ResponseEntity.ok(service.update(id, dto));
    }

    @DeleteMapping("delete")
    public ResponseEntity<?> deleteById(@RequestParam Long id) {
        if (service.read(id).equals(null)) {
            return ResponseEntity.notFound().build();
        } else {
            service.delete(id);
            return ResponseEntity.ok().build();
        }
    }

    @DeleteMapping("delete-user-liked-coffee")
    public ResponseEntity<?> deleteUserLikedCoffee(@RequestParam Long userId, @RequestParam Long coffeeId) {
        service.deleteUserLikedCoffee(coffeeId, userId);
        return ResponseEntity.ok().build();
    }
}


