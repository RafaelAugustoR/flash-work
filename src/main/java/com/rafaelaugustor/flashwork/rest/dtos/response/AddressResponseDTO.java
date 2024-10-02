package com.rafaelaugustor.flashwork.rest.dtos.response;

import com.rafaelaugustor.flashwork.domain.entities.User;
import com.rafaelaugustor.flashwork.domain.enums.AddressType;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.UUID;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class AddressResponseDTO {

    private UUID id;

    private String city;

    private String state;

    private String neighborhood;

    private String street;

    private String postalCode;

    private Integer houseNumber;

    private AddressType type;

    private Integer apartmentNumber;

    private String apartmentName;

    private User user;
}
