package com.example.democoffee.map.impl;

import com.example.democoffee.entity.Category;
import com.example.democoffee.map.MapInterface;
import com.example.democoffee.model.CategoryResponseDto;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")

public interface CategoryMap extends MapInterface<Category, CategoryResponseDto> {
}
