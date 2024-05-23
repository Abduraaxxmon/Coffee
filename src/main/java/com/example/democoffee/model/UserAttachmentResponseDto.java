package com.example.democoffee.model;

import lombok.*;

@Getter
@Setter
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class UserAttachmentResponseDto {
    private Long id;
    private String name;
    private String path;
    private String contentType;
    private String extension;

}
