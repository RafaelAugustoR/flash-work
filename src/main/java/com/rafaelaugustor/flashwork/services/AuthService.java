package com.rafaelaugustor.flashwork.services;

import com.rafaelaugustor.flashwork.domain.entities.User;
import com.rafaelaugustor.flashwork.domain.enums.UserRole;
import com.rafaelaugustor.flashwork.repositories.UserRepository;
import com.rafaelaugustor.flashwork.rest.dtos.request.LoginRequestDTO;
import com.rafaelaugustor.flashwork.rest.dtos.request.RegisterRequestDTO;
import com.rafaelaugustor.flashwork.rest.dtos.response.LoginResponseDTO;
import lombok.RequiredArgsConstructor;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class AuthService {

    private final UserRepository repository;
    private final AuthenticationManager authenticationManager;
    private final PasswordEncoder passwordEncoder;
    private final TokenService tokenService;

    public LoginResponseDTO login(LoginRequestDTO request) {
        var usernamePassword = new UsernamePasswordAuthenticationToken(request.getEmail(), request.getPassword());

        var auth = this.authenticationManager.authenticate(usernamePassword);

        var token = tokenService.generateToken((User) auth.getPrincipal());
        return new LoginResponseDTO(token);
    }

    public void register(RegisterRequestDTO request) {
        User user = repository.findByEmail(request.getEmail());

        if (user != null) {
            throw new RuntimeException("Already registered");
        }

        if (!request.getPassword().equals(request.getConfirmPassword())) {
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
                .password(passwordEncoder.encode(request.getPassword()))
                .role(UserRole.CUSTOMER)
                .build();

        repository.save(userToSave);
    }
}
