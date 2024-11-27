package com.rafaelaugustor.flashwork.domain.enums;

import lombok.Getter;
import lombok.RequiredArgsConstructor;

@Getter
@RequiredArgsConstructor
public enum ServiceStatus {

    OPEN("ABERTO"),
    IN_PROGRESS("EM ANDAMENTO"),
    IN_PROCESS("EM PROCESSO"),
    FINALIZED("FINALIZADO");

    private final String status;
}
