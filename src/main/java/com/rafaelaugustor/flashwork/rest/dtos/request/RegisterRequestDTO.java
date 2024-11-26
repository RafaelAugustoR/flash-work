package com.rafaelaugustor.flashwork.rest.dtos.request;

import com.rafaelaugustor.flashwork.domain.enums.UserRole;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.web.multipart.MultipartFile;

import java.time.LocalDate;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class RegisterRequestDTO {

    private String name;

    private String cpf;

    private String phone;

    private String email;

    private String profession;

    private String description;

    private LocalDate birthDate;

    private String password;

    private String confirmPassword;

    private MultipartFile profileImage;

    private UserRole role;
}
