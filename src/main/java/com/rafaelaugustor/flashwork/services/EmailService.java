package com.rafaelaugustor.flashwork.services;

import com.rafaelaugustor.flashwork.rest.dtos.request.EmailRequestDTO;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class EmailService {

    private final JavaMailSender mailSender;

    @Value("${EMAIL_USERNAME}")
    private String from;

    public void sendEmail(EmailRequestDTO request){

        var message = new SimpleMailMessage();

        message.setFrom(from);
        message.setTo(request.getTo());
        message.setSubject(request.getSubject());
        message.setText(request.getText());

        mailSender.send(message);

    }
}
