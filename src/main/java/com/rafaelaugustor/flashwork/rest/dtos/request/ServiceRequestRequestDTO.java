package com.rafaelaugustor.flashwork.rest.dtos.request;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.UUID;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class ServiceRequestRequestDTO {
    private UUID serviceId;
    private String description;
}
