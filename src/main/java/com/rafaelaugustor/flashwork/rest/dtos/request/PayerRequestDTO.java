package com.rafaelaugustor.flashwork.rest.dtos.request;

import lombok.Getter;
import lombok.Setter;

@Setter
@Getter
public class PayerRequestDTO {
    private String firstName;

    private String lastName;

    private String email;

    private PayerIdentificationRequestDTO identification;
}