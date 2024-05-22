package com.example.democoffee.service.Impl;

import com.example.democoffee.entity.Category;
import com.example.democoffee.entity.Coffee;
import com.example.democoffee.map.impl.CoffeeMap;
import com.example.democoffee.model.CoffeeAttachmentResponseDto;
import com.example.democoffee.model.CoffeeRequestDto;
import com.example.democoffee.model.CoffeeResponseDto;
import com.example.democoffee.repository.CategoryRepository;
import com.example.democoffee.repository.CoffeeRepository;
import com.example.democoffee.service.CardCountService;
import com.example.democoffee.service.CoffeeAttachmentService;
import com.example.democoffee.service.CoffeeService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import java.util.List;

@Service
@RequiredArgsConstructor
public class CoffeeServiceIml implements CoffeeService {
    private final CardCountService cardCountService;
    private final CoffeeRepository repository;
    private final CoffeeMap map;
    private final CategoryRepository catRepository;
    private final CoffeeAttachmentService attachmentService;
    @Override
    public CoffeeResponseDto create(CoffeeRequestDto dto) {
        Category category = catRepository.getReferenceById(dto.getCategory().getId());
        Coffee cof = Coffee.builder()
                .name(dto.getName())
                .description(dto.getDescription())
                .category(category)
                .cost(dto.getCost())
                .rate(dto.getRate())
                .size(dto.getSize())
                .build();

        return map.toDto(repository
                .save(cof));
    }

    @Override
    public CoffeeResponseDto read(Long id){
        return map.toDto(repository.findById(id).orElse(null));
    }

    @Override
    public CoffeeAttachmentResponseDto readWithAttachment(Long userId,Long coffeeId) {
        Coffee coffee = repository.getReferenceById(coffeeId);
        Long card = cardCountService.getCardCount(userId,coffeeId);

        String uri = ServletUriComponentsBuilder.fromCurrentContextPath()
                .path("/v1/coffee/download/attachment/download")
                .queryParam("id",coffee.getId())
                .toUriString();

        return  CoffeeAttachmentResponseDto.builder()
                .id(coffee.getId())
                .name(coffee.getName())
                .description(coffee.getDescription())
                .cost(coffee.getCost())
                .rate(coffee.getRate())
                .uri(uri)
                .size(coffee.getSize())
                .card(card)
                .build();

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
        coffee.setRate(dto.getRate());
        coffee.setSize(dto.getSize());
        coffee.setRate(dto.getRate());
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

    @Override
    public List<CoffeeResponseDto>getByCategory(Long category) {
        List<Coffee> coffeeList = repository.findAllByCategoryId(category);
        return map.toDtoList(coffeeList);
    }

    @Override
    public CoffeeResponseDto getByName(String name) {
        return map.toDto(repository.findByName(name));
    }

}
