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
public class ServiceController {

    private final ServiceService serviceService;

    @PostMapping
    public ResponseEntity<Void> createService(@RequestBody ServiceRequestDTO request, Principal principal) {
        serviceService.createService(request, principal);
        return ResponseEntity.ok().build();
    }

    @GetMapping("/{id}")
    public ResponseEntity<ServiceResponseDTO> getService(@PathVariable UUID id) {

        var service = serviceService.findServiceById(id);

        return ResponseEntity.ok().body(service);
    }

    @GetMapping("/all")
    public ResponseEntity<List<ServiceResponseDTO>> findServicesByCategory(@RequestParam UUID categoryId) {
        return ResponseEntity.ok().body(serviceService.findServicesByCategory(categoryId));
    }

    @PutMapping("/{id}")
    public ResponseEntity<ServiceResponseDTO> updateService(@PathVariable UUID id, @RequestBody ServiceRequestDTO request, Principal principal) {
        var service = serviceService.updateService(id, request, principal);
        return ResponseEntity.ok().body(service);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteService(@PathVariable UUID id, Principal principal) {
        serviceService.deleteService(id, principal);
        return ResponseEntity.ok().build();
    }
}
