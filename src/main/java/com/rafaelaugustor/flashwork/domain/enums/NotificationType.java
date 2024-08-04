package com.rafaelaugustor.flashwork.domain.enums;

import lombok.Getter;
import lombok.RequiredArgsConstructor;

@Getter
@RequiredArgsConstructor
public enum NotificationType {

    ALERT("Alert"),
    REMINDER("Reminder"),
    SYSTEM("System");

    private final String type;
}
