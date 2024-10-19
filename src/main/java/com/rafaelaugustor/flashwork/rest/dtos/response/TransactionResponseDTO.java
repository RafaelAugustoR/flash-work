package com.rafaelaugustor.flashwork.rest.dtos.response;

import lombok.AllArgsConstructor;
import lombok.Data;

import java.math.BigDecimal;
import java.time.LocalDateTime;

@Data
@AllArgsConstructor
public class TransactionResponseDTO {

    private BigDecimal amount;
    private String transactionType;
    private LocalDateTime transactionDate;
}