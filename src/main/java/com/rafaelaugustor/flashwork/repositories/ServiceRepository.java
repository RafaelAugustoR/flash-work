package com.rafaelaugustor.flashwork.repositories;

import com.rafaelaugustor.flashwork.domain.entities.Service;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.UUID;

public interface ServiceRepository extends JpaRepository<Service, UUID> {
    Service findByIdAndClientEmail(UUID serviceId, String email);

    List<Service> findByClientEmail(String email);

    List<Service> findByCategoriesId(UUID categoryId);
}