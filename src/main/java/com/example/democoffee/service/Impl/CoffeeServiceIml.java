package com.example.democoffee.service.Impl;

import com.example.democoffee.entity.Category;
import com.example.democoffee.entity.Coffee;
import com.example.democoffee.map.impl.CoffeeMap;
import com.example.democoffee.model.CoffeeRequestDto;
import com.example.democoffee.model.CoffeeResponseDto;
import com.example.democoffee.repository.CategoryRepository;
import com.example.democoffee.repository.CoffeeRepository;
import com.example.democoffee.service.CoffeeService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class CoffeeServiceIml implements CoffeeService {
    private final CoffeeRepository repository;
    private final CoffeeMap map;
    private final CategoryRepository catRepository;

    @Override
    public CoffeeResponseDto create(CoffeeRequestDto dto) {
        Category category = catRepository.getReferenceById(dto.getCategory().getId());
        Coffee cof = Coffee.builder()
                .name(dto.getName())
                .description(dto.getDescription())
                .category(category)
                .cost(dto.getCost())
                .build();

        return map.toDto(repository
                .save(cof));
    }

    @Override
    public CoffeeResponseDto read(Long id) {
        return map
                .toDto(repository
                        .getReferenceById(id));
    }

    @Override
    public List<CoffeeResponseDto> readAll() {
        return map
                .toDtoList(repository
                        .findAll());
    }

    @Override
    public CoffeeResponseDto update(Long id, CoffeeRequestDto dto) {
        Coffee coffee = repository.getReferenceById(id);
        coffee.setCategory(catRepository.getReferenceById(dto.getCategory().getId()));
        coffee.setName(dto.getName());
        coffee.setCost(dto.getCost());
        coffee.setDescription(dto.getDescription());

        return map
                .toDto(repository
                        .save(coffee));
    }

    @Override
    public void delete(Long id) {
        repository
                .deleteById(id);
    }
}
