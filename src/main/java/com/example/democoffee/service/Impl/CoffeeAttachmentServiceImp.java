package com.example.democoffee.service.Impl;

import com.example.democoffee.entity.UserAttachment;
import com.example.democoffee.entity.Coffee;
import com.example.democoffee.repository.UserAttachmentRepository;
import com.example.democoffee.repository.CoffeeRepository;
import com.example.democoffee.service.AttachmentWithCoffeeService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.multipart.MultipartHttpServletRequest;

import java.io.File;
import java.util.Iterator;
import java.util.Optional;
import java.util.Set;
import java.util.UUID;

@Service
@RequiredArgsConstructor
public class CoffeeAttachmentServiceImp implements AttachmentWithCoffeeService {
    private final UserAttachmentRepository userAttachmentRepository;
    private final CoffeeRepository coffeeRepository;

    @Override
    public UserAttachment upload(Long id, MultipartHttpServletRequest request) {
        Iterator<String> filename = request.getFileNames();
        Optional<Coffee> coffeeById = coffeeRepository.findById(id);

        File file = new File("uploadFile");
        if (!file.exists()) {
            file.mkdir();
        }
        while (filename.hasNext()) {
            String name = filename.next();
            MultipartFile multipartFile = request.getFile(name);

            File uploadfile = new File(
                    file.getAbsolutePath() + "/" +
                            UUID.randomUUID() + getExtention(multipartFile.getOriginalFilename())
            );

            UserAttachment userAttachment = new UserAttachment();
            userAttachment.setName(multipartFile.getName());
            userAttachment.setSize(multipartFile.getSize());
            userAttachment.setExtension(getExtention(multipartFile.getOriginalFilename()));
            userAttachment.setPath(uploadfile.getPath());

            userAttachmentRepository.save(userAttachment);
            if (coffeeById.isPresent()){
                Coffee coffee = coffeeById.get();
                Set<UserAttachment> coffeeAttachments = coffee.getAttachments();
                coffeeAttachments.add(userAttachment);

                coffee.setAttachments(coffeeAttachments);
                coffeeRepository.save(coffee);
            }

        }
        return null;

    }



    private String getExtention(String filename) {
        return filename.substring(filename.lastIndexOf("."));
    }
}
