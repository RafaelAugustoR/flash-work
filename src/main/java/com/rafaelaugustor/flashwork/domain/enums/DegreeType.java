package com.rafaelaugustor.flashwork.domain.enums;

import lombok.Getter;
import lombok.RequiredArgsConstructor;

@Getter
@RequiredArgsConstructor
public enum DegreeType {

    BACHELOR("BACHARELADO"),
    MASTER("MESTRADO"),
    DOCTORATE("DOUTORADO"),
    ASSOCIATE("TECNÓLOGO"),
    DIPLOMA("DIPLOMA");

    private final String degree;
}
