package com.example.democoffee.service.Impl;

import com.example.democoffee.entity.User;
import com.example.democoffee.entity.UserAttachment;
import com.example.democoffee.model.UserAttachmentResponseDto;
import com.example.democoffee.repository.UserAttachmentRepository;
import com.example.democoffee.repository.UserRepository;
import com.example.democoffee.service.UserAttachmentInterface;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.multipart.MultipartHttpServletRequest;

import java.io.File;
import java.io.IOException;
import java.time.LocalDate;
import java.util.*;

@Service
@RequiredArgsConstructor
public class UserAttachmentServiceImpl implements UserAttachmentInterface {

    private final UserAttachmentRepository attachmentrepository;
    private final UserRepository userRepository;

    @Override
    public ResponseEntity<?> upload(MultipartHttpServletRequest dto, Long id) {
        Iterator<String> filename = dto.getFileNames();
        LocalDate date = LocalDate.now();
        Optional<User> byUserId = userRepository.findById(id);


        File folder = new File("upload" + date);
        if (!folder.exists()) {
            folder.mkdir();
            UserAttachmentResponseDto userAttachmentResponseDto = new UserAttachmentResponseDto();

        }
        while (filename.hasNext()) {
            String files = filename.next();
            MultipartFile multipartFile = dto.getFile(files);


            File uploadfile = new File
                    (folder.getAbsolutePath() + "/" + UUID.randomUUID() + getExtention(multipartFile.getOriginalFilename()));

            try {
                multipartFile.transferTo(uploadfile);


                UserAttachment cooffeeAttachment = new UserAttachment();
                cooffeeAttachment.setName(multipartFile.getName());
                cooffeeAttachment.setSize(multipartFile.getSize());
                cooffeeAttachment.setContentType(multipartFile.getContentType());
                cooffeeAttachment.setExtension(getExtention(multipartFile.getOriginalFilename()));
                cooffeeAttachment.setPath(uploadfile.getPath());
                attachmentrepository.save(cooffeeAttachment);


                byUserId.ifPresent(user -> {
                    user.getAttachments().add(cooffeeAttachment);
                    userRepository.save(user);
                });
            } catch (IOException e) {
                throw new RuntimeException(e);
            }

        }
        return ResponseEntity.ok().build();
    }

    private String getExtention(String filename) {
        if (filename.isEmpty()) {
            return "";
        }
        return filename.substring(filename.lastIndexOf("."));
    }
}