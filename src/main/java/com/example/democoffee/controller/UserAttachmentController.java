package com.example.democoffee.controller;

import com.example.democoffee.service.Impl.CoffeeAttachmentAttachmentServiceImp;
import com.example.democoffee.service.UserAttachmentInterface;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartHttpServletRequest;

@RestController
@RequestMapping("/v1/-upload-attachment-user")
@RequiredArgsConstructor
public class UserAttachmentController {
    private final UserAttachmentInterface service;

    @PostMapping("/post")
    public ResponseEntity<?> upload(MultipartHttpServletRequest request, @RequestParam Long id){
        return ResponseEntity.ok(service.upload(request,id));
    }
//    @GetMapping("/getAll")
//    public ResponseEntity<?> getAll(){
//        return ResponseEntity.ok(service.getAll());
//    }
}
