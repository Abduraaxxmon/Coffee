package com.example.democoffee.service.Impl;

import com.example.democoffee.entity.CoffeeAttachment;
import com.example.democoffee.entity.Coffee;
import com.example.democoffee.model.CoffeeAttachmentResponseDto;
import com.example.democoffee.repository.CoffeeAttachmentRepository;
import com.example.democoffee.repository.CoffeeRepository;
import com.example.democoffee.service.CoffeeAttachmentService;
import lombok.RequiredArgsConstructor;
import lombok.SneakyThrows;
import org.apache.tomcat.util.file.ConfigurationSource;
import org.springframework.core.io.Resource;
import org.springframework.core.io.FileSystemResource;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.multipart.MultipartHttpServletRequest;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import java.io.File;
import java.time.LocalDate;
import java.util.*;

@Service
@RequiredArgsConstructor
public class CoffeeAttachmentAttachmentServiceImp implements CoffeeAttachmentService {
    private final CoffeeAttachmentRepository coffeeAttachmentRepository;
    private final CoffeeRepository coffeeRepository;

    @SneakyThrows
    @Override
    public CoffeeAttachment upload(Long id, MultipartHttpServletRequest request) {
        Iterator<String> filename = request.getFileNames();
        Optional<Coffee> coffeeById = coffeeRepository.findById(id);

        File file = new File("uploadFile"+ LocalDate.now());
        if (!file.exists()) {
            file.mkdir();
        }
        while (filename.hasNext()) {
            String name = filename.next();
            MultipartFile multipartFile = request.getFile(name);
            String ext = getExtention(multipartFile.getOriginalFilename());
            String fileUniqName = UUID.randomUUID().toString()+ext;

            File uploadfile = new File(
                    file.getAbsolutePath() + "/" +fileUniqName);

            CoffeeAttachment cooffeeAttachment = new CoffeeAttachment();
            cooffeeAttachment.setName(multipartFile.getName());
            cooffeeAttachment.setSize(multipartFile.getSize());
            cooffeeAttachment.setExtension(ext);
            cooffeeAttachment.setContentType(multipartFile.getContentType());
            cooffeeAttachment.setPath(uploadfile.getPath());
            cooffeeAttachment.setCoffee(coffeeById.get());

            coffeeAttachmentRepository.save(cooffeeAttachment);

            multipartFile.transferTo(uploadfile);
            if (coffeeById.isPresent()){
                Coffee coffee = coffeeById.get();
                Set<CoffeeAttachment> coffeeAttachments = coffee.getAttachments();
                coffeeAttachments.add(cooffeeAttachment);

                coffee.setAttachments(coffeeAttachments);
                coffeeRepository.save(coffee);
            }

        }
        return null;

    }

    @Override
    public List<CoffeeAttachmentResponseDto> getAll() {

        List<CoffeeAttachmentResponseDto> coffeeAttachments = new ArrayList<>();
        Iterable<CoffeeAttachment> iterator = coffeeAttachmentRepository.findAll();

        iterator.forEach( iter ->{
            Optional<Coffee> coffeeOpt = coffeeRepository.findById(iter.getCoffee().getId());
            Coffee coffee  = coffeeOpt.get();

            String uri = ServletUriComponentsBuilder.fromCurrentContextPath()
                    .path("/v1/-upload-attachment/download")
                    .queryParam("id", iter.getId())
                    .toUriString();

            CoffeeAttachmentResponseDto dto = CoffeeAttachmentResponseDto.builder()
                    .id(iter.getId())
                    .cost(coffee.getCost())
                    .name(iter.getName())
                    .description(coffee.getDescription())
                    .uri(uri)
                    .build();

            coffeeAttachments.add(dto);
        });

         return coffeeAttachments;
    }

    @Override
    public ResponseEntity<Resource> dowload(Long id) {

        Optional<CoffeeAttachment> attachment = coffeeAttachmentRepository.findById(id);

        if (attachment.isPresent()) {
        CoffeeAttachment coffeeAttachment = attachment.get();

        File file = new File(coffeeAttachment.getPath());
        if (file.exists()) {
            Resource resource = new FileSystemResource(file);
            return ResponseEntity.ok()
                    .contentType(MediaType.APPLICATION_OCTET_STREAM)
                    .header(HttpHeaders.CONTENT_DISPOSITION,"attachment; filename=\""+coffeeAttachment.getName()+"\""+coffeeAttachment.getExtension())
                    .body(resource);
        }
    }
        return ResponseEntity.notFound().build();
    }


    private String getExtention(String filename) {
        return filename.substring(filename.lastIndexOf("."));
    }
}
