package com.example.democoffee.service.Impl;

import com.example.democoffee.entity.Category;
import com.example.democoffee.map.impl.CategoryMap;
import com.example.democoffee.model.CategoryRequestDto;
import com.example.democoffee.model.CategoryResponseDto;
import com.example.democoffee.repository.CategoryRepository;
import com.example.democoffee.service.CategoryService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class CategoryServiceImpl implements CategoryService {
    private final CategoryRepository repository;
    private final CategoryMap map;


    @Override
    public CategoryResponseDto create(CategoryRequestDto dto) {
        Category category = Category.builder()
                .name(dto.getName())
                .build();
        return map.toDto(repository.save(category));
    }

    @Override
    public CategoryResponseDto read(Long id) {
        return map.toDto(repository.getReferenceById(id));
    }

    @Override
    public List<CategoryResponseDto> readAll() {
        return map.toDtoList(repository.findAll());
    }

    @Override
    public CategoryResponseDto update(Long id, CategoryRequestDto dto) {
        Category category = repository.getReferenceById(id);
        category.setName(dto.getName());
        return map.toDto(repository.save(category));
    }

    @Override
    public void delete(Long id) {
        repository.deleteById(id);
    }
}
