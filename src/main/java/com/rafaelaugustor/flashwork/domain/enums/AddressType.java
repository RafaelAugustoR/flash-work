package com.rafaelaugustor.flashwork.domain.enums;

import lombok.Getter;
import lombok.RequiredArgsConstructor;

@Getter
@RequiredArgsConstructor
public enum AddressType {

    HOUSE("CASA"),
    APARTMENT("APARTAMENTO");

    private final String name;
}