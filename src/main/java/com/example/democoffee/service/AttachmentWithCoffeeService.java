package com.example.democoffee.service;

import com.example.democoffee.entity.UserAttachment;
import org.springframework.web.multipart.MultipartHttpServletRequest;

public interface AttachmentWithCoffeeService  {
    public UserAttachment upload(Long id, MultipartHttpServletRequest request);
}
