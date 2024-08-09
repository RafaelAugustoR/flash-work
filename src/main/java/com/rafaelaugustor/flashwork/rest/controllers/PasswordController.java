package com.rafaelaugustor.flashwork.rest.controllers;

import com.rafaelaugustor.flashwork.rest.dtos.request.ResetPasswordRequestDTO;
import com.rafaelaugustor.flashwork.services.PasswordService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Map;

import static com.rafaelaugustor.flashwork.utils.Constants.APP_ROOT;

@RestController
@RequestMapping(APP_ROOT + "/forgot-password")
@RequiredArgsConstructor
@CrossOrigin("*")
public class PasswordController {

    private final PasswordService passwordService;

    @PostMapping
    public ResponseEntity<Void> forgotPassword(@RequestBody Map<String, String> email) {
        try {
            passwordService.sendOtp(email);
            return ResponseEntity.ok().build();
        }
        catch (IllegalArgumentException e) {
            return ResponseEntity.badRequest().build();
        }
    }

    @PostMapping("/verify-otp")
    public ResponseEntity<Void> verifyOtp(@RequestParam String email,
                                          @RequestBody Map<String, String> otp) {
        try {
            passwordService.verifyOtp(email, otp);
            return ResponseEntity.ok().build();
        } catch (IllegalArgumentException e) {
            return ResponseEntity.badRequest().build();
        }
    }


    @PostMapping("/reset-password")
    public ResponseEntity<Void> resetPassword(@RequestParam String email,
                                                @RequestBody ResetPasswordRequestDTO request) {
        try {
            passwordService.resetPassword(email, request);
            return ResponseEntity.ok().build();
        }catch (IllegalArgumentException e){
            return ResponseEntity.badRequest().build();
        }
    }
}
