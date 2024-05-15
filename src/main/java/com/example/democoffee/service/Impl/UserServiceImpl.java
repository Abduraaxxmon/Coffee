package com.example.democoffee.service.Impl;

import com.example.democoffee.entity.User;
import com.example.democoffee.map.impl.UserMap;
import com.example.democoffee.model.UserRequestDto;
import com.example.democoffee.model.UserResponseDto;
import com.example.democoffee.repository.UserAttachmentRepository;
import com.example.democoffee.repository.UserRepository;
import com.example.democoffee.service.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
@RequiredArgsConstructor
public class UserServiceImpl implements UserService {
    private final UserRepository repository;
    private final UserAttachmentRepository userAttachmentRepository;

    private final UserMap map;

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


}
