package com.rafaelaugustor.flashwork.services;

import com.rafaelaugustor.flashwork.domain.entities.User;
import com.rafaelaugustor.flashwork.repositories.UserRepository;
import com.rafaelaugustor.flashwork.rest.dtos.request.EmailRequestDTO;
import com.rafaelaugustor.flashwork.rest.dtos.request.ResetPasswordRequestDTO;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.Map;
import java.util.Optional;
import java.util.Random;
import java.util.concurrent.TimeUnit;

@Service
@RequiredArgsConstructor
@Slf4j
public class PasswordService {

    private final EmailService emailService;

    private final UserRepository userRepository;

    private final PasswordEncoder passwordEncoder;

    private final StringRedisTemplate redisTemplate;

    private final Random random = new Random();


    public void sendOtp(Map<String, String> email) {
        var emailValue = email.get("email");

        getUserByEmail(emailValue)
                .orElseThrow(() -> new IllegalArgumentException("User not found."));

        String otp = generateOtp();
        redisTemplate.opsForValue().set(emailValue, otp, 15, TimeUnit.MINUTES);
        sendOtpEmail(emailValue, otp);
    }

    public void verifyOtp(String email, Map<String, String> otp) {
        String storedOtp = redisTemplate.opsForValue().get(email);
        if (storedOtp == null || !otp.get("otp").equals(storedOtp)) {
            throw new IllegalArgumentException("Invalid OTP provided.");
        }
    }

    public void resetPassword(String email, ResetPasswordRequestDTO request) {

        User user = getUserByEmail(email)
                .orElseThrow(() -> new IllegalArgumentException("User not found."));

        if(request.getPassword().matches(request.getPassword())){
            user.setPassword(passwordEncoder.encode(request.getPassword()));

            userRepository.save(user);
        }
    }

    private void sendOtpEmail(String to, String otp) {
        EmailRequestDTO request = EmailRequestDTO.builder()
                .to(to)
                .subject("Your OTP request:")
                .text(otp)
                .build();
        emailService.sendEmail(request);
    }

    private String generateOtp() {
        int otp = 100000 + random.nextInt(900000);
        return String.valueOf(otp);
    }

    private Optional<User> getUserByEmail(String email) {
        return Optional.ofNullable(userRepository.findByEmail(email));
    }
}
