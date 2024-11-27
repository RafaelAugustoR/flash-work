package com.rafaelaugustor.flashwork.rest.controllers;

import com.rafaelaugustor.flashwork.domain.enums.WorkType;
import com.rafaelaugustor.flashwork.rest.dtos.request.ServiceRequestDTO;
import com.rafaelaugustor.flashwork.rest.dtos.response.ServiceResponseDTO;
import com.rafaelaugustor.flashwork.services.ServiceService;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.security.Principal;
import java.util.UUID;

import static com.rafaelaugustor.flashwork.utils.Constants.APP_ROOT;

@RestController
@RequestMapping(APP_ROOT + "/services")
@RequiredArgsConstructor
@CrossOrigin
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

    @GetMapping("/categories/{id}")
    public Page<ServiceResponseDTO> findServicesByCategory(
            @PathVariable UUID id,
            Pageable pageable) {
        return serviceService.findServicesByCategory(id, pageable);
    }

    @GetMapping("/user")
    public Page<ServiceResponseDTO> findServicesByUser(Principal principal,
                                                       Pageable pageable) {
        return serviceService.findServicesByUser(principal, pageable);
    }

    @GetMapping("/work-type/{workType}")
    public Page<ServiceResponseDTO> findServicesByWorkType(@PathVariable WorkType workType,
                                                           Pageable pageable) {
        return serviceService.findServicesByWorkType(workType, pageable);
    }

    @GetMapping("/city/{addressId}")
    public Page<ServiceResponseDTO> findServicesByUserLocation(@PathVariable UUID addressId,
                                                               Pageable pageable) {
        return serviceService.findServicesByCity(addressId, pageable);
    }

    @PutMapping("/{id}")
    public ResponseEntity<ServiceResponseDTO> updateService(@PathVariable UUID id, @RequestBody ServiceRequestDTO request, Principal principal) {
        var service = serviceService.update(id, request, principal);
        return ResponseEntity.ok().body(service);
    }

    @PutMapping("/{serviceId}/finalize")
    public ResponseEntity<ServiceResponseDTO> markServiceAsFinalized(
            @PathVariable UUID serviceId,
            Principal principal) {

        ServiceResponseDTO response = serviceService.markAsFinalized(serviceId, principal);

        return ResponseEntity.ok(response);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteService(@PathVariable UUID id, Principal principal) {
        serviceService.delete(id, principal);
        return ResponseEntity.ok().build();
    }
}
