package com.example.democoffee.controller;

import com.example.democoffee.service.Impl.UserAttachmentServiceImpl;
import lombok.RequiredArgsConstructor;
import org.springframework.core.io.Resource;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartHttpServletRequest;

@RestController
@RequestMapping("/v1/user/attachment")
@RequiredArgsConstructor
public class UserAttachmentController {
    private final UserAttachmentServiceImpl service;

    @PostMapping("/post")
    public ResponseEntity<?> upload(MultipartHttpServletRequest request, @RequestParam Long id) {
        return ResponseEntity.ok(service.upload(request, id));
    }

    @GetMapping("/download")
    public ResponseEntity<Resource> download(@RequestParam Long id) {
        return service.download(id);
    }
}
