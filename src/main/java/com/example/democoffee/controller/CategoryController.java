package com.example.democoffee.controller;

import com.example.democoffee.model.CategoryRequestDto;
import com.example.democoffee.service.CategoryService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("api/v1/category")
@RequiredArgsConstructor
public class CategoryController {
    private final CategoryService service;

    @PostMapping("/create")
    public ResponseEntity<?> create(@RequestBody CategoryRequestDto dto) {
        return ResponseEntity.ok(service.create(dto));
    }

    @GetMapping("/read")
    public ResponseEntity<?> getById(@RequestParam Long id) {
        return ResponseEntity.ok(service.read(id));
    }

    @GetMapping("/readAll")
    public ResponseEntity<?> getAll() {
        return ResponseEntity.ok(service.readAll());
    }

    @PutMapping("/update")
    public ResponseEntity<?> updateById(@RequestParam Long id, @RequestBody CategoryRequestDto dto) {
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
}


