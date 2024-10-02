package com.rafaelaugustor.flashwork.services;

import com.rafaelaugustor.flashwork.domain.entities.Address;
import com.rafaelaugustor.flashwork.domain.entities.User;
import com.rafaelaugustor.flashwork.repositories.AddressRepository;
import com.rafaelaugustor.flashwork.repositories.UserRepository;
import com.rafaelaugustor.flashwork.rest.dtos.request.AddressRequestDTO;
import com.rafaelaugustor.flashwork.rest.dtos.response.AddressResponseDTO;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.security.Principal;
import java.util.List;
import java.util.UUID;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class AddressService {

    private final AddressRepository addressRepository;
    private final UserRepository userRepository;

    public void create(AddressRequestDTO request, Principal principal) {
        User user = userRepository.findByEmail(principal.getName());

        if (user == null) {
            throw new IllegalArgumentException("User not found");
        }

        if (user.getAddresses().size() >= 3) {
            throw new RuntimeException("User has too many addresses");
        }

        Address addressToSave = Address.builder()
                .state(request.getState())
                .city(request.getCity())
                .neighborhood(request.getNeighborhood())
                .street(request.getStreet())
                .houseNumber(request.getHouseNumber())
                .postalCode(request.getPostalCode())
                .type(request.getType())
                .apartmentName(request.getApartmentName())
                .apartmentNumber(request.getApartmentNumber())
                .user(user)
                .build();

        addressRepository.save(addressToSave);
    }

    public AddressResponseDTO update(UUID addressId, AddressRequestDTO request, Principal principal) {

        Address address = addressRepository.findByIdAndUserEmail(addressId, principal.getName())
                .orElseThrow(() -> new IllegalArgumentException("Address not found or does not belong to the user"));

        address.setState(request.getState());
        address.setCity(request.getCity());
        address.setNeighborhood(request.getNeighborhood());
        address.setStreet(request.getStreet());
        address.setHouseNumber(request.getHouseNumber());
        address.setPostalCode(request.getPostalCode());
        address.setType(request.getType());
        address.setApartmentName(request.getApartmentName());
        address.setApartmentNumber(request.getApartmentNumber());

        addressRepository.save(address);

        return toResponseDTO(address);
    }

    public List<AddressResponseDTO> findAllAddresses(Principal principal) {
        List<Address> addresses = addressRepository.findAllByUserEmail(principal.getName());

        return addresses.stream()
                .map(this::toResponseDTO)
                .collect(Collectors.toList());
    }

    public AddressResponseDTO findById(UUID addressId, Principal principal) {
        Address address = addressRepository.findByIdAndUserEmail(addressId, principal.getName())
                .orElseThrow(() -> new IllegalArgumentException("Address not found or does not belong to the user"));

        return toResponseDTO(address);
    }

    public void delete(UUID addressId, Principal principal) {

        Address address = addressRepository.findByIdAndUserEmail(addressId, principal.getName())
                .orElseThrow(() -> new IllegalArgumentException("Address not found or does not belong to the user"));

        addressRepository.delete(address);
    }

    private AddressResponseDTO toResponseDTO(Address address) {
        return AddressResponseDTO.builder()
                .id(address.getId())
                .state(address.getState())
                .city(address.getCity())
                .neighborhood(address.getNeighborhood())
                .street(address.getStreet())
                .houseNumber(address.getHouseNumber())
                .postalCode(address.getPostalCode())
                .type(address.getType())
                .apartmentName(address.getApartmentName())
                .apartmentNumber(address.getApartmentNumber())
                .build();
    }
}