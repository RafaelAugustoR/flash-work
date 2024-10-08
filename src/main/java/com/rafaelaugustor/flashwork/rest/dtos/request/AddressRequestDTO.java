package com.rafaelaugustor.flashwork.rest.dtos.request;

import com.rafaelaugustor.flashwork.domain.entities.User;
import com.rafaelaugustor.flashwork.domain.enums.AddressType;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class AddressRequestDTO {

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
