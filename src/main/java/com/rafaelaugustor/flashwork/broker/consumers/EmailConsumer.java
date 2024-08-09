package com.rafaelaugustor.flashwork.broker.consumers;

import com.rafaelaugustor.flashwork.rest.dtos.request.EmailRequestDTO;
import com.rafaelaugustor.flashwork.services.EmailService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.messaging.handler.annotation.Payload;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
@Slf4j
public class EmailConsumer {

    private final EmailService emailService;

    @RabbitListener(queues = "${spring.rabbitmq.queue.email}")
    public void listen(@Payload EmailRequestDTO request) {
        try {
             emailService.sendEmail(request);
        } catch (Exception e) {
            log.error("Erro ao processar e-mail da fila", e);
        }
    }
}
