package com.rafaelaugustor.flashwork.broker.producers;

import com.rafaelaugustor.flashwork.rest.dtos.request.EmailRequestDTO;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
@Slf4j
public class EmailProducer {

    private final RabbitTemplate rabbitTemplate;

    @Value("${spring.rabbitmq.queue.email}")
    private String emailQueue;

    public void sendEmail(EmailRequestDTO emailRequest) {
        try {
            rabbitTemplate.convertAndSend(emailQueue, emailRequest);
        } catch (Exception e) {
            log.error("Erro ao enviar e-mail para a fila", e);
        }
    }
}
