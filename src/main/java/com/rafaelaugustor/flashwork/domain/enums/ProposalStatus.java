package com.rafaelaugustor.flashwork.domain.enums;

import lombok.Getter;
import lombok.RequiredArgsConstructor;

@Getter
@RequiredArgsConstructor
public enum ProposalStatus {
    PENDING("AGUARDANDO"),
    ACCEPTED("ACEITO"),
    REJECTED("REJEITADO"),
    CANCELLED("CANCELLED");

    private final String status;
}
