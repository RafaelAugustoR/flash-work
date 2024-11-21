package com.rafaelaugustor.flashwork.services;

import com.rafaelaugustor.flashwork.domain.entities.Service;
import com.rafaelaugustor.flashwork.domain.enums.ServiceStatus;
import com.rafaelaugustor.flashwork.domain.enums.WorkType;
import com.rafaelaugustor.flashwork.repositories.CategoryRepository;
import com.rafaelaugustor.flashwork.repositories.ProposalRepository;
import com.rafaelaugustor.flashwork.repositories.ServiceRepository;
import com.rafaelaugustor.flashwork.repositories.UserRepository;
import com.rafaelaugustor.flashwork.rest.dtos.request.ServiceRequestDTO;
import com.rafaelaugustor.flashwork.rest.dtos.response.CategoryResponseDTO;
import com.rafaelaugustor.flashwork.rest.dtos.response.ServiceResponseDTO;
import com.rafaelaugustor.flashwork.rest.dtos.response.UserMinDTO;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.security.Principal;
import java.util.ArrayList;
import java.util.UUID;

@org.springframework.stereotype.Service
@RequiredArgsConstructor
public class ServiceService {

    private final ServiceRepository serviceRepository;
    private final CategoryRepository categoryRepository;
    private final UserRepository userRepository;
    private final ProposalRepository proposalRepository;

    public void create(ServiceRequestDTO request, Principal principal) {
        var user = userRepository.findByEmail(principal.getName());

        var categories = categoryRepository.findAllById(request.getCategories());

        var service = Service.builder()
                .title(request.getTitle())
                .description(request.getDescription())
                .budget(request.getBudget())
                .workType(request.getWorkType())
                .deadline(request.getDeadline())
                .addressId(request.getAddressId())
                .status(ServiceStatus.OPEN)
                .client(user)
                .categories(new ArrayList<>(categories))
                .build();

        serviceRepository.save(service);
    }

    public ServiceResponseDTO update(UUID serviceId, ServiceRequestDTO request, Principal principal) {
        var service = serviceRepository.findByIdAndClientEmail(serviceId, principal.getName());

        if (service != null) {
            var categories = categoryRepository.findAllById(request.getCategories());

            service.setTitle(request.getTitle());
            service.setDescription(request.getDescription());
            service.setBudget(request.getBudget());
            service.setDeadline(request.getDeadline());
            service.setWorkType(request.getWorkType());
            service.setAddressId(request.getAddressId());
            service.setCategories(new ArrayList<>(categories));

            serviceRepository.save(service);
        }

        assert service != null;
        return toResponseDTO(service);
    }

    public void delete(UUID serviceId, Principal principal) {
        var service = serviceRepository.findByIdAndClientEmail(serviceId, principal.getName());

        assert service != null;
        serviceRepository.delete(service);
    }

    public ServiceResponseDTO findById(UUID serviceId) {
        Service service = serviceRepository.findById(serviceId).
                orElseThrow(() -> new RuntimeException("Service not found"));

        return toResponseDTO(service);
    }

    public Page<ServiceResponseDTO> findServicesByCategory(UUID categoryId, Pageable pageable) {
        Page<Service> services = serviceRepository.findByCategoriesId(categoryId, pageable);

        return services.map(this::toResponseDTO);
    }

    public Page<ServiceResponseDTO> findServicesByUser(Principal principal, Pageable pageable) {
        Page<Service> services = serviceRepository.findByClientEmail(principal.getName(), pageable);

        return services.map(this::toResponseDTO);
    }

    public Page<ServiceResponseDTO> findServicesByWorkType(WorkType workType, Pageable pageable) {
        Page<Service> services = serviceRepository.findByWorkType(workType, pageable);

        return services.map(this::toResponseDTO);
    }

    @Transactional
    public Page<ServiceResponseDTO> findServicesByUserLocation(Principal principal, Pageable pageable) {
        var user = userRepository.findByEmail(principal.getName());

        var addressId = user.getAddresses().stream()
                .map(address -> address.getId())
                .filter(id -> id != null)
                .findFirst()
                .orElseThrow(() -> new RuntimeException("User has no valid addresses."));

        var services = serviceRepository.findByAddressIdExcludingClient(addressId, principal.getName(), pageable);

        return services.map(this::toResponseDTO);
    }

    private ServiceResponseDTO toResponseDTO(Service service) {
        long proposalCount = proposalRepository.countByServiceId(service.getId());
        return ServiceResponseDTO.builder()
                .id(service.getId())
                .title(service.getTitle())
                .description(service.getDescription())
                .budget(service.getBudget())
                .deadline(service.getDeadline())
                .workType(service.getWorkType().getType())
                .addressId(service.getAddressId())
                .createdAt(service.getCreatedAt())
                .client(new UserMinDTO(service.getClient()))
                .categories(service.getCategories().stream()
                        .map(CategoryResponseDTO::new)
                        .toList())
                .proposalQuantity((int) proposalCount)
                .build();
    }
}