package com.rafaelaugustor.flashwork.domain.enums;

import lombok.Getter;
import lombok.RequiredArgsConstructor;

@Getter
@RequiredArgsConstructor
public enum NotificationType {

    ALERT("ALERTA"),
    REMINDER("LEMBRETE"),
    SYSTEM("SISTEMA");

    private final String type;
}
