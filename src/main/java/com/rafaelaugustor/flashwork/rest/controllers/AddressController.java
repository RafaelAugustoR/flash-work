package com.rafaelaugustor.flashwork.rest.controllers;

import com.rafaelaugustor.flashwork.rest.dtos.request.AddressRequestDTO;
import com.rafaelaugustor.flashwork.rest.dtos.response.AddressResponseDTO;
import com.rafaelaugustor.flashwork.services.AddressService;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.security.Principal;
import java.util.List;
import java.util.UUID;

import static com.rafaelaugustor.flashwork.utils.Constants.APP_ROOT;

@RestController
@RequestMapping(APP_ROOT + "/address")
@RequiredArgsConstructor
@CrossOrigin
public class AddressController {

    private final AddressService addressService;

    @PostMapping
    public ResponseEntity<Void> createAddress(@RequestBody AddressRequestDTO request, Principal principal) {
        addressService.create(request, principal);
        return ResponseEntity.ok().build();
    }

    @PutMapping("/{id}")
    public ResponseEntity<Void> updateAddress(@PathVariable UUID id, @RequestBody AddressRequestDTO request, Principal principal) {
        addressService.update(id, request, principal);
        return ResponseEntity.ok().build();
    }

    @GetMapping
    public ResponseEntity<Page<AddressResponseDTO>> findAllAddresses(Principal principal, Pageable pageable) {
        Page<AddressResponseDTO> addresses = addressService.findAll(principal, pageable);
        return ResponseEntity.ok().body(addresses);
    }

    @GetMapping("/{id}")
    public ResponseEntity<AddressResponseDTO> findAddressById(@PathVariable UUID id, Principal principal) {
        AddressResponseDTO address = addressService.findById(id, principal);
        return ResponseEntity.ok().body(address);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteAddress(@PathVariable UUID id, Principal principal) {
        addressService.delete(id, principal);
        return ResponseEntity.ok().build();
    }
}

