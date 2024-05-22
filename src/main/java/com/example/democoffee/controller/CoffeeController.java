package com.example.democoffee.controller;

import com.example.democoffee.model.CoffeeRequestDto;
import com.example.democoffee.model.CoffeeResponseDto;
import com.example.democoffee.service.CoffeeService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("api/v1/coffee")
@RequiredArgsConstructor
public class CoffeeController {
    private final CoffeeService service;

    @PostMapping("/create")
    public ResponseEntity<?> create(@RequestBody CoffeeRequestDto dto) {
        return ResponseEntity.ok(service.create(dto));
    }

    @GetMapping("/read")
    public ResponseEntity<?> getById(@RequestParam Long id) {
        return ResponseEntity.ok(service.read(id));
    }

    @GetMapping("/read-with-attachment")
    public ResponseEntity<?> getByIdWithAttachment(@RequestParam Long userId, @RequestParam Long coffeeId) {
        return ResponseEntity.ok(service.readWithAttachment(userId,coffeeId));
    }

    @GetMapping("/readAll")
    public ResponseEntity<?> getAll() {
        return ResponseEntity.ok(service.readAll());
    }

    @GetMapping("/getByCategory")
    public ResponseEntity<?> getByCategory(@RequestParam Long id) {
        return ResponseEntity.ok(service.getByCategory(id));
    }

    @PutMapping("/update")
    public ResponseEntity<?> updateById(@RequestParam Long id, @RequestBody CoffeeRequestDto dto) {
        return ResponseEntity.ok(service.update(id, dto));
    }

    @GetMapping("/getByName")
    public CoffeeResponseDto getByName(@RequestParam String name) {
        return service.getByName(name);
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
}


