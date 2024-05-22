package com.example.democoffee.service;

import com.example.democoffee.model.CoffeeAttachmentResponseDto;
import com.example.democoffee.model.CoffeeRequestDto;
import com.example.democoffee.model.CoffeeResponseDto;

public interface CoffeeService extends ServiceInterface<CoffeeRequestDto, CoffeeResponseDto>{
    Object getByCategory(Long category);
    CoffeeAttachmentResponseDto readWithAttachment(Long id);
    CoffeeResponseDto getByName(String name);

}
