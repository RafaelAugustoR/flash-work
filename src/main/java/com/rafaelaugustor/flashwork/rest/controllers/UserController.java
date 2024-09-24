package com.rafaelaugustor.flashwork.rest.controllers;

import com.rafaelaugustor.flashwork.rest.dtos.request.UserRequestDTO;
import com.rafaelaugustor.flashwork.rest.dtos.response.UserResponseDTO;
import com.rafaelaugustor.flashwork.services.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.security.Principal;
import java.util.List;
import java.util.UUID;

import static com.rafaelaugustor.flashwork.utils.Constants.APP_ROOT;

@RestController
@RequestMapping(APP_ROOT + "/user")
@RequiredArgsConstructor
@CrossOrigin("*")
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
    public ResponseEntity<UserResponseDTO> findById(@PathVariable UUID id) {
        UserResponseDTO userProfile = service.findById(id);
        return ResponseEntity.ok().body(userProfile);
    }

    @GetMapping("/all")
    public ResponseEntity<List<UserResponseDTO>> listAllUsers() {
        List<UserResponseDTO> users = service.listAllUsers();
        return ResponseEntity.ok().body(users);
    }

}
