package com.example.democoffee.service;

import com.example.democoffee.entity.CoffeeAttachment;

import java.util.List;

public interface ServiceInterface<Request,Response>{
    Response create(Request request);
    Response read(Long id);
    List<Response> readAll();
    Response update(Long id,Request request);
    void delete(Long id);


}
