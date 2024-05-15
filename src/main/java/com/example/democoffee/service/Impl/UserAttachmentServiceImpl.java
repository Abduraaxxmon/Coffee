package com.example.democoffee.service.Impl;

import com.example.democoffee.entity.UserAttachment;
import com.example.democoffee.entity.User;
import com.example.democoffee.model.UserAttachmentResponseDto;
import com.example.democoffee.repository.UserAttachmentRepository;
import com.example.democoffee.repository.UserRepository;
import com.example.democoffee.service.AttachmentInterface;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.multipart.MultipartHttpServletRequest;

import java.io.File;
import java.io.IOException;
import java.time.LocalDate;
import java.util.*;

@Service
@RequiredArgsConstructor
public class UserAttachmentServiceImpl implements AttachmentInterface {

    private final UserAttachmentRepository attachmentrepository;
    private final UserRepository userRepository;

    @Override
    public UserAttachment upload(MultipartHttpServletRequest dto, Long id) {
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


                UserAttachment userAttachment = new UserAttachment();
                userAttachment.setName(multipartFile.getName());
                userAttachment.setSize(multipartFile.getSize());
                userAttachment.setContentType(multipartFile.getContentType());
                userAttachment.setExtension(getExtention(multipartFile.getOriginalFilename()));
                userAttachment.setPath(uploadfile.getPath());
                attachmentrepository.save(userAttachment);


                byUserId.ifPresent(user -> {
                    user.getAttachments().add(userAttachment);
                    userRepository.save(user);
                });
            } catch (IOException e) {
                throw new RuntimeException(e);
            }

        }
        return null;
    }

    private String getExtention(String filename) {
        if (filename.isEmpty()) {
            return "";
        }
        return filename.substring(filename.lastIndexOf("."));
    }
}