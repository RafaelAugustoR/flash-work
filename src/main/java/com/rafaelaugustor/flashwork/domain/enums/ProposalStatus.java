package com.rafaelaugustor.flashwork.domain.enums;

import lombok.Getter;
import lombok.RequiredArgsConstructor;

@Getter
@RequiredArgsConstructor
public enum ProposalStatus {
    PENDING("AGUARDANDO"),
    ACCEPTED("ACEITO"),
    REJECTED("REJEITADO");

    private final String status;
}
