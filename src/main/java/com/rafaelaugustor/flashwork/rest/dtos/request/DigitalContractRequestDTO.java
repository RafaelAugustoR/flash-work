package com.rafaelaugustor.flashwork.rest.dtos.request;

import com.rafaelaugustor.flashwork.rest.dtos.response.ServiceResponseDTO;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.util.UUID;

@Getter
@AllArgsConstructor
@NoArgsConstructor
public class DigitalContractRequestDTO {
    private UUID clientId;
    private UUID freelancerId;
    private ServiceResponseDTO service;
}
