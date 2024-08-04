package com.rafaelaugustor.flashwork.domain.enums;

import lombok.Getter;
import lombok.RequiredArgsConstructor;

@Getter
@RequiredArgsConstructor
public enum WorkType {

    REMOTE("Remote"),
    ONSITE("Onsite");

    private final String type;

}
