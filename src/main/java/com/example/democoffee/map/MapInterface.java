package com.example.democoffee.map;

import java.util.List;

public interface MapInterface<Entity,Dto> {
    Dto toDto (Entity entity);
    Entity toEntity(Dto dto);
    List<Dto> toDtoList(List<Entity> entities);
    List<Entity> toEntityList(List<Dto> dtos);
}
