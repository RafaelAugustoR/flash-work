package com.rafaelaugustor.flashwork.services;

import com.rafaelaugustor.flashwork.domain.entities.ServiceRequest;
import com.rafaelaugustor.flashwork.domain.enums.ServiceRequestStatus;
import com.rafaelaugustor.flashwork.repositories.ServiceRepository;
import com.rafaelaugustor.flashwork.repositories.ServiceRequestRepository;
import com.rafaelaugustor.flashwork.repositories.UserRepository;
import com.rafaelaugustor.flashwork.rest.dtos.request.ServiceRequestRequestDTO;
import com.rafaelaugustor.flashwork.rest.dtos.response.ServiceRequestResponseDTO;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.security.Principal;
import java.sql.Timestamp;
import java.time.Instant;
import java.util.UUID;

@Service
@RequiredArgsConstructor
public class ServiceRequestService {

    private final ServiceRequestRepository serviceRequestRepository;
    private final ServiceRepository serviceRepository;
    private final UserRepository userRepository;

    public ServiceRequestResponseDTO createServiceRequest(ServiceRequestRequestDTO request, Principal principal) {
        var user = userRepository.findByEmail(principal.getName());
        var service = serviceRepository.findById(request.getServiceId())
                .orElseThrow(() -> new IllegalArgumentException("Service not found"));

        var serviceRequest = ServiceRequest.builder()
                .service(service)
                .requester(user)
                .status(ServiceRequestStatus.PENDING)
                .description(request.getDescription())
                .requestedAt(Timestamp.from(Instant.now()))
                .build();

        serviceRequestRepository.save(serviceRequest);

        return toResponseDTO(serviceRequest);
    }

    public ServiceRequestResponseDTO updateServiceRequestStatus(UUID requestId, ServiceRequestStatus status, Principal principal) {
        var serviceRequest = serviceRequestRepository.findByIdAndRequesterEmail(requestId, principal.getName())
                .orElseThrow(() -> new IllegalArgumentException("Service Request not found"));

        serviceRequest.setStatus(status);

        if (status == ServiceRequestStatus.COMPLETED) {
            serviceRequest.setCompletedAt(Timestamp.from(Instant.now()));
        }

        serviceRequestRepository.save(serviceRequest);

        return toResponseDTO(serviceRequest);
    }

    public ServiceRequestResponseDTO respondToServiceRequest(UUID requestId, ServiceRequestStatus status, Principal principal) {
        var serviceRequest = serviceRequestRepository.findById(requestId)
                .orElseThrow(() -> new IllegalArgumentException("Service Request not found"));

        var service = serviceRequest.getService();
        var serviceProvider = service.getProvider();

        if (!serviceProvider.getEmail().equals(principal.getName())) {
            throw new SecurityException("User is not authorized to respond to this request");
        }

        serviceRequest.setStatus(status);

        serviceRequestRepository.save(serviceRequest);

        return toResponseDTO(serviceRequest);
    }


    private ServiceRequestResponseDTO toResponseDTO(ServiceRequest serviceRequest) {
        return ServiceRequestResponseDTO.builder()
                .id(serviceRequest.getId())
                .serviceId(serviceRequest.getService().getId())
                .requesterId(serviceRequest.getRequester().getId())
                .status(serviceRequest.getStatus())
                .description(serviceRequest.getDescription())
                .requestedAt(serviceRequest.getRequestedAt())
                .completedAt(serviceRequest.getCompletedAt())
                .build();
    }
}
