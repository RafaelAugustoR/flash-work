package com.rafaelaugustor.flashwork.rest.dtos.request;

import lombok.Getter;
import lombok.Setter;

import java.math.BigDecimal;

@Setter
@Getter
public class PixPaymentRequestDTO {

    private BigDecimal transactionAmount;

    private PayerRequestDTO payer;
}