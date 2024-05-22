package com.example.democoffee.service.Impl;

import com.example.democoffee.entity.Coffee;
import com.example.democoffee.entity.User;
import com.example.democoffee.map.impl.UserMap;
import com.example.democoffee.model.CoffeeAttachmentResponseDto;
import com.example.democoffee.model.UserRequestDto;
import com.example.democoffee.model.UserResponseDto;
import com.example.democoffee.repository.CoffeeRepository;
import com.example.democoffee.repository.UserAttachmentRepository;
import com.example.democoffee.repository.UserRepository;
import com.example.democoffee.service.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import java.util.ArrayList;
import java.util.List;

@Service
@RequiredArgsConstructor
public class UserServiceImpl implements UserService {
    private final UserRepository repository;
    private final UserAttachmentRepository userAttachmentRepository;
    private final CoffeeRepository coffeeRepository;

    private final UserMap map;
    private final UserRepository userRepository;

    @Override
    public UserResponseDto create(UserRequestDto dto) {
        User user = User.builder()
                .firstName(dto.getFirstname())
                .lastname(dto.getLastname())
                .date(dto.getDate())
                .build();
        return map
                .toDto(repository
                        .save(user));
    }

    @Override
    public UserResponseDto read(Long id) {
        return map
                .toDto(repository
                        .getReferenceById(id));
    }

    @Override
    public List<UserResponseDto> readAll() {
        return map
                .toDtoList(new ArrayList<>(repository.findAll()));
    }

    @Override
    public UserResponseDto update(Long id, UserRequestDto dto) {
        User user = repository.getReferenceById(id);
        user.setFirstName(dto.getFirstname());
        user.setLastname(dto.getLastname());
        user.setDate(dto.getDate());
        return map
                .toDto(repository
                        .save(user));
    }

    @Override
    public void delete(Long id) {
        repository.deleteById(id);
    }

    @Override
    public List<CoffeeAttachmentResponseDto> readUserLikedCoffee(Long id) {
        Iterable<Coffee> coffees = userRepository.findCoffeesLikedByUser(id);
        List<CoffeeAttachmentResponseDto> attachments = new ArrayList<>();


        coffees.forEach(iter -> {

            String uri = ServletUriComponentsBuilder.fromCurrentContextPath()
                    .path("/v1/coffee/attachment/download")
                    .queryParam("id", iter.getId())
                    .toUriString();

            CoffeeAttachmentResponseDto dto = CoffeeAttachmentResponseDto.builder()
                    .id(iter.getId())
                    .description(iter.getDescription())
                    .cost(iter.getCost())
                    .name(iter.getName())
                    .rate(iter.getRate())
                    .uri(uri)
                    .build();
            attachments.add(dto);
        });

        return attachments;
    }

    @Override
    public void deleteUserLikedCoffee(Long coffeeId, Long userId) {
        User user = repository.findById(userId).orElseThrow(() -> new RuntimeException("User not found"));
        Coffee coffee = coffeeRepository.findById(coffeeId).orElseThrow(() -> new RuntimeException("Coffee not found"));
        user.getLikes().remove(coffee);
        userRepository.save(user);
    }

    @Override
    public void updateUserLikedCoffee(Long coffeeId, Long userId) {
        User user = repository.findById(userId).orElseThrow(() -> new RuntimeException("User not found"));
        Coffee coffee = coffeeRepository.findById(coffeeId).orElseThrow(() -> new RuntimeException("Coffee not found"));
        if (!user.getLikes().contains(coffee)) {
            user.getLikes().add(coffee);
            userRepository.save(user);
        }
    }


}
