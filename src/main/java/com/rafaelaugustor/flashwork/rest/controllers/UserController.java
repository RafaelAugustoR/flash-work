package com.rafaelaugustor.flashwork.rest.controllers;

import com.rafaelaugustor.flashwork.rest.dtos.request.UserRequestDTO;
import com.rafaelaugustor.flashwork.rest.dtos.response.UserResponseDTO;
import com.rafaelaugustor.flashwork.services.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.security.Principal;
import java.util.UUID;

import static com.rafaelaugustor.flashwork.utils.Constants.APP_ROOT;

@RestController
@RequestMapping(APP_ROOT + "/users")
@RequiredArgsConstructor
@CrossOrigin
public class UserController {

    private final UserService service;

    @GetMapping("/me")
    public ResponseEntity<UserResponseDTO> findUserByToken(Principal principal){
        var user = service.findUserByToken(principal);
        return ResponseEntity.ok().body(user);
    }

    @PutMapping
    public ResponseEntity<Void> updateUser(@RequestBody UserRequestDTO request, Principal principal){
         service.update(request, principal);
        return ResponseEntity.noContent().build();
    }

    @DeleteMapping
    public ResponseEntity<Void> deleteUser(Principal principal){
        service.delete(principal);
        return ResponseEntity.noContent().build();
    }

    @GetMapping("/{id}/profile")
    public ResponseEntity<UserResponseDTO> findUserById(@PathVariable UUID id) {
        UserResponseDTO userProfile = service.findById(id);
        return ResponseEntity.ok().body(userProfile);
    }

    @GetMapping("/all")
    public Page<UserResponseDTO> listAllUsers(Pageable pageable) {
        return service.listAllUsers(pageable);
    }

}
