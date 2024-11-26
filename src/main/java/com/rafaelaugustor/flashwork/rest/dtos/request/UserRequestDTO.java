package com.rafaelaugustor.flashwork.rest.dtos.request;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.web.multipart.MultipartFile;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class UserRequestDTO {

    private String name;

    private String phone;

    private String profession;

    private String description;

    private MultipartFile profileImage;
}
