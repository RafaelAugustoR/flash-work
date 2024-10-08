package com.rafaelaugustor.flashwork.domain.enums;

import lombok.Getter;
import lombok.RequiredArgsConstructor;

@Getter
@RequiredArgsConstructor
public enum WorkType {

    REMOTE("REMOTO"),
    ONSITE("PRESENCIAL"),
    HYBRID("HÍBRIDO");

    private final String type;

}
