package com.rafaelaugustor.flashwork.rest.controllers;

import com.rafaelaugustor.flashwork.rest.dtos.request.RegisterRequestDTO;
import com.rafaelaugustor.flashwork.services.AuthService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import static com.rafaelaugustor.flashwork.utils.Constants.APP_ROOT;

@RestController
@RequestMapping(APP_ROOT + "/auth")
@RequiredArgsConstructor
@CrossOrigin("*")
public class AuthController {

    private final AuthService service;

    @PostMapping("/register")
    public ResponseEntity<Void> register(@RequestBody RegisterRequestDTO request){
        service.register(request);
        return ResponseEntity.ok().build();
    }
}
