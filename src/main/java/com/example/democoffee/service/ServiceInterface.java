package com.example.democoffee.service;

import org.springframework.http.ResponseEntity;

public interface ServiceInterface<Request,Response>{
    ResponseEntity<?> create(Request request);
    ResponseEntity<?> read(Long id);
    ResponseEntity<?> readAll();
    ResponseEntity<?> update(Long id,Request request);
    ResponseEntity<?> delete(Long id);


}
