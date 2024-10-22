package com.rafaelaugustor.flashwork.rest.controllers;

import com.rafaelaugustor.flashwork.domain.enums.WorkType;
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
    public ResponseEntity<List<ServiceResponseDTO>> findServicesByCategory(@PathVariable UUID id) {
        return ResponseEntity.ok().body(serviceService.findServicesByCategory(id));
    }

    @GetMapping("/user")
    public ResponseEntity<List<ServiceResponseDTO>> findServicesByUser(Principal principal) {
        return ResponseEntity.ok().body(serviceService.findServicesByUser(principal));
    }

    @GetMapping("/work-type/{workType}")
    public ResponseEntity<List<ServiceResponseDTO>> findServicesByWorkType(@PathVariable WorkType workType) {
        List<ServiceResponseDTO> services = serviceService.findServicesByWorkType(workType);
        return ResponseEntity.ok(services);
    }

    @GetMapping("/location")
    public ResponseEntity<List<ServiceResponseDTO>> findServicesByUserLocation(Principal principal) {
        List<ServiceResponseDTO> services = serviceService.findServicesByUserLocation(principal);
        return ResponseEntity.ok(services);
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
