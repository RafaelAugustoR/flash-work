package com.rafaelaugustor.flashwork.rest.dtos.response;

import com.rafaelaugustor.flashwork.domain.enums.UserRole;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;
import java.util.UUID;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class UserResponseDTO {

    private UUID id;

    private String name;

    private String email;

    private String cpf;

    private String phone;

    private String profession;

    private String description;

    private String profileImage;

    private LocalDate birthDate;

    private WalletResponseDTO wallet;

    private UserRole role;
}
