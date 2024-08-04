package com.rafaelaugustor.flashwork.domain.enums;

import lombok.Getter;
import lombok.RequiredArgsConstructor;

@Getter
@RequiredArgsConstructor
public enum DegreeType {

    BACHELOR("Bachelor"),
    MASTER("Master"),
    DOCTORATE("Doctorate"),
    ASSOCIATE("Associate"),
    DIPLOMA("Diploma");

    private final String degree;
}
