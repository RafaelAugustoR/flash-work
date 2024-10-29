package com.rafaelaugustor.flashwork.rest.controllers;

import com.rafaelaugustor.flashwork.rest.dtos.request.PixPaymentRequestDTO;
import com.rafaelaugustor.flashwork.rest.dtos.request.WithdrawRequestDTO;
import com.rafaelaugustor.flashwork.rest.dtos.response.PixPaymentResponseDTO;
import com.rafaelaugustor.flashwork.services.PaymentService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.security.Principal;

import static com.rafaelaugustor.flashwork.utils.Constants.APP_ROOT;

@RestController
@RequestMapping(APP_ROOT + "/process_payment")
@RequiredArgsConstructor
public class PaymentController {
    private final PaymentService paymentService;

    @PostMapping("/pix")
    public ResponseEntity<PixPaymentResponseDTO> processPixPayment(@RequestBody PixPaymentRequestDTO pixPaymentDTO, Principal principal) {
        PixPaymentResponseDTO payment = paymentService.processPixPayment(pixPaymentDTO, principal);
        return ResponseEntity.status(HttpStatus.CREATED).body(payment);
    }

    @PostMapping("/withdraw")
    public ResponseEntity<Void> processWithdrawal(@RequestBody WithdrawRequestDTO request, Principal principal) {
        paymentService.processWithDraw(request, principal);
        return ResponseEntity.ok().build();
    }
}
