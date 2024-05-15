package com.example.democoffee.map.impl;

import com.example.democoffee.entity.CoffeeAttachment;
import com.example.democoffee.map.MapInterface;
import com.example.democoffee.model.CoffeeAttachmentResponseDto;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface CoffeeAttachmentMap extends MapInterface<CoffeeAttachment, CoffeeAttachmentResponseDto> {
}
