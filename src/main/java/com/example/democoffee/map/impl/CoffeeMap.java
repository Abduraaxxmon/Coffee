package com.example.democoffee.map.impl;

import com.example.democoffee.entity.Coffee;
import com.example.democoffee.map.MapInterface;
import com.example.democoffee.model.CoffeeResponseDto;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")

public interface CoffeeMap extends MapInterface<Coffee, CoffeeResponseDto> {
}
