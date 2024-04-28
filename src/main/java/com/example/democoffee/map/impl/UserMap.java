package com.example.democoffee.map.impl;

import com.example.democoffee.entity.User;
import com.example.democoffee.map.MapInterface;
import com.example.democoffee.model.UserResponseDto;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface UserMap extends MapInterface<User, UserResponseDto> {
}
