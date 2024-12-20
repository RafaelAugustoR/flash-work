package com.rafaelaugustor.flashwork.services;

import com.rafaelaugustor.flashwork.domain.entities.Service;
import com.rafaelaugustor.flashwork.domain.enums.ServiceStatus;
import com.rafaelaugustor.flashwork.domain.enums.WorkType;
import com.rafaelaugustor.flashwork.repositories.*;
import com.rafaelaugustor.flashwork.rest.dtos.request.ServiceRequestDTO;
import com.rafaelaugustor.flashwork.rest.dtos.response.CategoryResponseDTO;
import com.rafaelaugustor.flashwork.rest.dtos.response.ServiceResponseDTO;
import com.rafaelaugustor.flashwork.rest.dtos.response.TransactionResponseDTO;
import com.rafaelaugustor.flashwork.rest.dtos.response.UserMinDTO;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.Pageable;

import java.math.BigDecimal;
import java.security.Principal;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;
import java.util.stream.Collectors;

@org.springframework.stereotype.Service
@RequiredArgsConstructor
public class ServiceService {

    private final ServiceRepository serviceRepository;
    private final CategoryRepository categoryRepository;
    private final UserRepository userRepository;
    private final AddressRepository addressRepository;
    private final ProposalRepository proposalRepository;
    private final WalletRepository walletRepository;
    private final WalletService walletService;

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

        if (!services.hasContent()) {
            Page<Service> allServices = serviceRepository.findAll(pageable);

            Page<Service> filteredServices = filterExpiredServices(allServices, pageable);

            return filteredServices.map(this::toResponseDTO);
        }

        Page<Service> filteredServices = filterExpiredServices(services, pageable);

        return filteredServices.map(this::toResponseDTO);
    }

    public ServiceResponseDTO markAsFinalized(UUID serviceId, Principal principal) {
        Service service = serviceRepository.findByIdAndClientEmail(serviceId, principal.getName());

        if (service.getStatus() == ServiceStatus.FINALIZED) {
            throw new RuntimeException("Service is already finalized");
        }

        service.setStatus(ServiceStatus.FINALIZED);

        TransactionResponseDTO transactionResponse = walletService.transfer(
                service.getClient().getId(),
                service.getFreelancer().getId(),
                BigDecimal.valueOf(1000.0)
        );

        serviceRepository.save(service);

        return toResponseDTO(service);
    }

    public Page<ServiceResponseDTO> findServicesByUserAndStatus(Principal principal, Pageable pageable, ServiceStatus status) {
        Page<Service> services = serviceRepository.findByClientEmailAndStatus(principal.getName(), status, pageable);

        return services.map(this::toResponseDTO);
    }

    public Page<ServiceResponseDTO> findServicesByWorkType(WorkType workType, Pageable pageable) {
        Page<Service> services = serviceRepository.findByWorkType(workType, pageable);

        return services.map(this::toResponseDTO);
    }

    @Transactional
    public Page<ServiceResponseDTO> findServicesByCity(UUID addressId, Pageable pageable) {
        var address = addressRepository.findById(addressId)
                .orElseThrow(() -> new RuntimeException("Address not found"));

        var city = address.getCity();

        var services = serviceRepository.findByCityExcludingClient(city, address.getUser().getEmail(), pageable);

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
                .workType(service.getWorkType())
                .addressId(service.getAddressId())
                .createdAt(service.getCreatedAt())
                .status(service.getStatus())
                .client(new UserMinDTO(service.getClient()))
                .freelancer(service.getFreelancer() != null ? new UserMinDTO(service.getFreelancer()) : null)
                .categories(service.getCategories().stream()
                        .map(CategoryResponseDTO::new)
                        .toList())
                .proposalQuantity((int) proposalCount)
                .contractId(service.getContract() != null ? service.getContract().getId() : null)
                .build();
    }

    private Page<Service> filterExpiredServices(Page<Service> services, Pageable pageable) {
        List<Service> filteredServices = services.getContent().stream()
                .filter(service -> !service.getDeadline().isBefore(LocalDate.now()))
                .collect(Collectors.toList());

        return new PageImpl<>(filteredServices, pageable, services.getTotalElements());
    }

}