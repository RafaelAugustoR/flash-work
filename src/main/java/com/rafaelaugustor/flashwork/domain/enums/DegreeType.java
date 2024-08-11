package com.rafaelaugustor.flashwork.domain.enums;

import lombok.Getter;
import lombok.RequiredArgsConstructor;

@Getter
@RequiredArgsConstructor
public enum DegreeType {

    BACHELOR("BACHELOR"),
    MASTER("MASTER"),
    DOCTORATE("DOCTORATE"),
    ASSOCIATE("ASSOCIATE"),
    DIPLOMA("DIPLOMA");

    private final String degree;
}
