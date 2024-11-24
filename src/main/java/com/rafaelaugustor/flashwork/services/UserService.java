package com.rafaelaugustor.flashwork.services;

import com.rafaelaugustor.flashwork.domain.entities.User;
import com.rafaelaugustor.flashwork.repositories.UserRepository;
import com.rafaelaugustor.flashwork.rest.dtos.request.UserRequestDTO;
import com.rafaelaugustor.flashwork.rest.dtos.response.UserResponseDTO;
import com.rafaelaugustor.flashwork.rest.dtos.response.WalletResponseDTO;
import jakarta.persistence.EntityNotFoundException;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.security.Principal;
import java.util.UUID;

@Service
@RequiredArgsConstructor
public class UserService {

    private final UserRepository repository;

    public UserResponseDTO findUserByToken(Principal principal) {
        User user = repository.findByEmail(principal.getName());
        return toResponseDTO(user);
    }

    public void update(UserRequestDTO request, Principal principal) {
        User user = repository.findByEmail(principal.getName());

        user.setName(request.getName());
        user.setProfession(request.getProfession());
        user.setPhone(request.getPhone());
        user.setDescription(request.getDescription());
        user.setProfilePicture(request.getProfilePicture());

        repository.save(user);
    }

    public void delete(Principal principal) {
        User user = repository.findByEmail(principal.getName());
        repository.delete(user);
    }

    public UserResponseDTO findById(UUID userId) {
        User user = repository.findById(userId)
                .orElseThrow(() -> new EntityNotFoundException("User not found"));

        return toResponseDTO(user);
    }

    public Page<UserResponseDTO> listAllUsers(Pageable pageable) {
        Page<User> users = repository.findAll(pageable);
        return users.map(this::toResponseDTO);
    }

    public UserResponseDTO toResponseDTO(User user) {

        return UserResponseDTO.builder()
                .id(user.getId())
                .name(user.getName())
                .cpf(user.getCpf())
                .birthDate(user.getBirthDate())
                .email(user.getEmail())
                .profilePicture(user.getProfilePicture())
                .role(user.getRole())
                .description(user.getDescription())
                .phone(user.getPhone())
                .profession(user.getProfession())
                .wallet(new WalletResponseDTO(user.getWallet()))
                .build();
    }
}
