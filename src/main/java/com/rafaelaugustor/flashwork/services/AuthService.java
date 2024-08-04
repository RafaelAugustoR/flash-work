package com.rafaelaugustor.flashwork.services;

import com.rafaelaugustor.flashwork.domain.entities.User;
import com.rafaelaugustor.flashwork.domain.enums.UserRole;
import com.rafaelaugustor.flashwork.repositories.UserRepository;
import com.rafaelaugustor.flashwork.rest.dtos.request.RegisterRequestDTO;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class AuthService {

    private final UserRepository repository;

    public void register(RegisterRequestDTO request){

        User user = repository.findByEmail(request.getEmail());

        if(user != null){
            throw new RuntimeException("Already registered");
        }

        if (!request.getPassword().equals(request.getConfirmPassword())){
            throw new RuntimeException("Password don't matches");
        }

        User userToSave = User.builder()
                .name(request.getName())
                .cpf(request.getCpf())
                .phone(request.getPhone())
                .email(request.getEmail())
                .birthDate(request.getBirthDate())
                .profession(request.getProfession())
                .description(request.getDescription())
                .password(request.getPassword())
                .profilePicture(request.getProfilePicture())
                .role(UserRole.CUSTOMER)
                .build();

        repository.save(userToSave);

    }
}
