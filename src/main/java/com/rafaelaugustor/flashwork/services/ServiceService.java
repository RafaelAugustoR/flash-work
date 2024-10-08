package com.rafaelaugustor.flashwork.services;

import com.rafaelaugustor.flashwork.domain.entities.Category;
import com.rafaelaugustor.flashwork.domain.entities.Service;
import com.rafaelaugustor.flashwork.repositories.CategoryRepository;
import com.rafaelaugustor.flashwork.repositories.ServiceRepository;
import com.rafaelaugustor.flashwork.repositories.UserRepository;
import com.rafaelaugustor.flashwork.rest.dtos.request.ServiceRequestDTO;
import com.rafaelaugustor.flashwork.rest.dtos.response.ServiceResponseDTO;
import lombok.RequiredArgsConstructor;
import org.springframework.transaction.annotation.Transactional;

import java.security.Principal;
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

    @Transactional
    public void createService(ServiceRequestDTO request, Principal principal) {
        var user = userRepository.findByEmail(principal.getName());

        var categories = categoryRepository.findAllById(request.getCategoryIds());

        var service = Service.builder()
                .title(request.getTitle())
                .description(request.getDescription())
                .price(request.getPrice())
                .workType(request.getWorkType())
                .location(request.getLocation())
                .client(user)
                .categories(new ArrayList<>(categories))
                .build();

        serviceRepository.save(service);
    }

    public ServiceResponseDTO updateService(UUID serviceId, ServiceRequestDTO request, Principal principal) {
        var service = serviceRepository.findByIdAndClientEmail(serviceId, principal.getName());

        if (service != null) {
            var categories = categoryRepository.findAllById(request.getCategoryIds());

            service.setTitle(request.getTitle());
            service.setDescription(request.getDescription());
            service.setPrice(request.getPrice());
            service.setWorkType(request.getWorkType());
            service.setLocation(request.getLocation());
            service.setCategories(new ArrayList<>(categories));

            serviceRepository.save(service);
        }

        assert service != null;
        return toResponseDTO(service);
    }

    public void deleteService(UUID serviceId, Principal principal){
        var service = serviceRepository.findByIdAndClientEmail(serviceId, principal.getName());

        assert service != null;
        serviceRepository.delete(service);
    }

    public ServiceResponseDTO findServiceById(UUID serviceId){
        Service service = serviceRepository.findById(serviceId).
                orElseThrow(() -> new RuntimeException("Service not found"));

        return toResponseDTO(service);
    }

    public List<ServiceResponseDTO> findServicesByCategory(UUID categoryId) {
        List<Service> services = serviceRepository.findByCategoriesId(categoryId);

        return services.stream()
                .map(this::toResponseDTO)
                .collect(Collectors.toList());
    }

    public List<ServiceResponseDTO> findServicesByUser(Principal principal){
        List<Service> services = serviceRepository.findByClientEmail(principal.getName());

        return services.stream()
                .map(this::toResponseDTO)
                .collect(Collectors.toList());
    }

    private ServiceResponseDTO toResponseDTO(Service service) {
        return ServiceResponseDTO.builder()
                .id(service.getId())
                .title(service.getTitle())
                .description(service.getDescription())
                .price(service.getPrice())
                .workType(service.getWorkType())
                .location(service.getLocation())
                .createdAt(service.getCreatedAt())
                .clientId(service.getClient().getId())
                .categories(service.getCategories().stream().map(Category::getName).toList())
                .build();
    }
}
       
