package com.rafaelaugustor.flashwork.rest.dtos.request;

import lombok.Getter;
import lombok.Setter;

@Setter
@Getter
public class PayerIdentificationRequestDTO {

    private String type;

    private String number;
}