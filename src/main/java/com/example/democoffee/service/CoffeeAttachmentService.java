package com.example.democoffee.service;

import com.example.democoffee.entity.CoffeeAttachment;
import com.example.democoffee.model.CoffeeAttachmentResponseDto;
import org.springframework.core.io.Resource;
import org.springframework.http.ResponseEntity;
import org.springframework.web.multipart.MultipartHttpServletRequest;

import java.util.List;

public interface CoffeeAttachmentService {
    public CoffeeAttachment upload(Long id, MultipartHttpServletRequest request);
    public List<CoffeeAttachmentResponseDto> getAll();

    ResponseEntity<Resource> dowload(Long id);

}
