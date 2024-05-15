package com.example.democoffee.controller;

import com.example.democoffee.entity.UserAttachment;
import com.example.democoffee.service.AttachmentInterface;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartHttpServletRequest;

@RestController
@RequestMapping("/v1/-upload-attachment")
@RequiredArgsConstructor
public class AttachmentController {
    private final AttachmentInterface service;

    @PostMapping("/post")
    public UserAttachment upload(MultipartHttpServletRequest request, @RequestParam Long id){
        return service.upload(request,id);
    }
}
