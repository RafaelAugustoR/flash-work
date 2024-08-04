package com.rafaelaugustor.flashwork.domain.enums;

import lombok.Getter;
import lombok.RequiredArgsConstructor;

@Getter
@RequiredArgsConstructor
public enum ReviewType {

    SERVICE("Service"),
    USER("User");

    private final String type;
}
