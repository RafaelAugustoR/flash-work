package com.rafaelaugustor.flashwork.domain.enums;

import lombok.Getter;
import lombok.RequiredArgsConstructor;

@Getter
@RequiredArgsConstructor
public enum TransactionType {

    DEPOSIT("Depósito"),
    WITHDRAWAL("Saque"),
    TRANSFER("Transferência");

    private final String transactionType;
}