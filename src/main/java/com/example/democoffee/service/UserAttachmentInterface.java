package com.example.democoffee.service;

import org.springframework.http.ResponseEntity;
import org.springframework.web.multipart.MultipartHttpServletRequest;

public interface UserAttachmentInterface {
    public ResponseEntity<?> upload(MultipartHttpServletRequest  dto, Long id);
}
