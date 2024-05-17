package com.example.democoffee.controller;

import com.example.democoffee.service.Impl.CoffeeAttachmentAttachmentServiceImp;
import lombok.RequiredArgsConstructor;
import org.springframework.core.io.Resource;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartHttpServletRequest;

@RestController
@RequestMapping("/v1/-upload-attachment")
@RequiredArgsConstructor
public class CoffeeAttachmentController {
    private final CoffeeAttachmentAttachmentServiceImp service;

    @PostMapping("/post")
    public ResponseEntity<?> upload(MultipartHttpServletRequest request, @RequestParam Long id){
        return ResponseEntity.ok(service.upload(id,request));
    }
    @GetMapping("/getAll")
    public ResponseEntity<?> getAll(){
        return ResponseEntity.ok(service.getAll());
    }

    @GetMapping("download")
    public ResponseEntity<Resource> download(@RequestParam Long id){
        return service.dowload(id);
    }
}
