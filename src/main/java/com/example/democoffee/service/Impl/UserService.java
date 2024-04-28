package com.example.democoffee.service.Impl;

import com.example.democoffee.repository.UserRepository;
import com.example.democoffee.service.ServiceInterface;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class UserService implements ServiceInterface {
    private final UserRepository repository;
    private final
    @Override
    public ResponseEntity<?> create(Object o) {
        return null;
    }

    @Override
    public ResponseEntity<?> read(Long id) {
        return null;
    }

    @Override
    public ResponseEntity<?> readAll() {
        return null;
    }

    @Override
    public ResponseEntity<?> update(Long id, Object o) {
        return null;
    }

    @Override
    public ResponseEntity<?> delete(Long id) {
        return null;
    }
}
