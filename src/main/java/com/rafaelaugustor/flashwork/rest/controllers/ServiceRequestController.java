package com.rafaelaugustor.flashwork.rest.controllers;

import com.rafaelaugustor.flashwork.domain.enums.ServiceRequestStatus;
import com.rafaelaugustor.flashwork.rest.dtos.request.ServiceRequestRequestDTO;
import com.rafaelaugustor.flashwork.rest.dtos.response.ServiceRequestResponseDTO;
import com.rafaelaugustor.flashwork.services.ServiceRequestService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.security.Principal;
import java.util.UUID;

import static com.rafaelaugustor.flashwork.utils.Constants.APP_ROOT;

@RestController
@RequestMapping(APP_ROOT + "/service-request")
@RequiredArgsConstructor
public class ServiceRequestController {

    private final ServiceRequestService serviceRequestService;

    @PostMapping
    public ResponseEntity<ServiceRequestResponseDTO> createServiceRequest(@RequestBody ServiceRequestRequestDTO request, Principal principal) {
        var serviceRequest = serviceRequestService.createServiceRequest(request, principal);
        return ResponseEntity.ok().body(serviceRequest);
    }

    @PatchMapping("/{requestId}/respond")
    public ResponseEntity<ServiceRequestResponseDTO> respondToServiceRequest(
            @PathVariable UUID requestId,
            @RequestParam ServiceRequestStatus status,
            Principal principal) {
        ServiceRequestResponseDTO response = serviceRequestService.respondToServiceRequest(requestId, status, principal);
        return new ResponseEntity<>(response, HttpStatus.OK);
    }

    @PutMapping("/{id}/status")
    public ResponseEntity<ServiceRequestResponseDTO> updateServiceRequestStatus(@PathVariable UUID id, @RequestParam ServiceRequestStatus status, Principal principal) {
        var serviceRequest = serviceRequestService.updateServiceRequestStatus(id, status, principal);
        return ResponseEntity.ok().body(serviceRequest);
    }
}
