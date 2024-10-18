package com.rafaelaugustor.flashwork.rest.dtos.response;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

@Setter
@Getter
@AllArgsConstructor
public class PixPaymentResponseDTO {
    private Long id;
    private String status;
    private String detail;
    private String qrCodeBase64;
    private String qrCode;
}