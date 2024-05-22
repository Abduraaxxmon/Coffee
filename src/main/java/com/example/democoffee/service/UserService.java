package com.example.democoffee.service;

import com.example.democoffee.entity.CoffeeAttachment;
import com.example.democoffee.model.CoffeeAttachmentResponseDto;
import com.example.democoffee.model.UserRequestDto;
import com.example.democoffee.model.UserResponseDto;
import com.example.democoffee.service.ServiceInterface;

import java.util.List;

public interface UserService extends ServiceInterface<UserRequestDto, UserResponseDto> {
    List<CoffeeAttachmentResponseDto> readUserLikedCoffee(Long id);
    void deleteUserLikedCoffee(Long coffeeId, Long userId);
    void updateUserLikedCoffee(Long coffeeId, Long userId);
}
