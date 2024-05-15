package com.example.democoffee.service;

import com.example.democoffee.entity.UserAttachment;
import org.springframework.web.multipart.MultipartHttpServletRequest;

public interface AttachmentInterface {
    public UserAttachment upload(MultipartHttpServletRequest  dto, Long id);
}
