package com.rafaelaugustor.flashwork.rest.controllers;

import com.rafaelaugustor.flashwork.rest.dtos.request.ServiceRequestDTO;
import com.rafaelaugustor.flashwork.rest.dtos.response.ServiceResponseDTO;
import com.rafaelaugustor.flashwork.services.ServiceService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.security.Principal;
import java.util.List;
import java.util.UUID;

import static com.rafaelaugustor.flashwork.utils.Constants.APP_ROOT;

@RestController
@RequestMapping(APP_ROOT + "/services")
@RequiredArgsConstructor
@CrossOrigin("*")
public class ServiceController {

    private final ServiceService serviceService;

    @PostMapping
    public ResponseEntity<Void> createService(@RequestBody ServiceRequestDTO request, Principal principal) {
        serviceService.create(request, principal);
        return ResponseEntity.ok().build();
    }

    @GetMapping("/{id}")
    public ResponseEntity<ServiceResponseDTO> findServiceById(@PathVariable UUID id) {

        var service = serviceService.findById(id);

        return ResponseEntity.ok().body(service);
    }

    @GetMapping
    public ResponseEntity<List<ServiceResponseDTO>> findServicesByCategory(@RequestParam UUID categoryId) {
        return ResponseEntity.ok().body(serviceService.findServicesByCategory(categoryId));
    }

    @GetMapping
    public ResponseEntity<List<ServiceResponseDTO>> findServicesByCategory(Principal principal) {
        return ResponseEntity.ok().body(serviceService.findServicesByUser(principal));
    }

    @PutMapping("/{id}")
    public ResponseEntity<ServiceResponseDTO> updateService(@PathVariable UUID id, @RequestBody ServiceRequestDTO request, Principal principal) {
        var service = serviceService.update(id, request, principal);
        return ResponseEntity.ok().body(service);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteService(@PathVariable UUID id, Principal principal) {
        serviceService.delete(id, principal);
        return ResponseEntity.ok().build();
    }
}
