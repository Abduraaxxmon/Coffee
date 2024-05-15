package com.example.democoffee.map.impl;

import com.example.democoffee.entity.UserAttachment;
import com.example.democoffee.map.MapInterface;
import com.example.democoffee.model.UserAttachmentResponseDto;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface UserAttachmentMap extends MapInterface<UserAttachment, UserAttachmentResponseDto> {
}
