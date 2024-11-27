package com.rafaelaugustor.flashwork.repositories;

import com.rafaelaugustor.flashwork.domain.entities.Service;
import com.rafaelaugustor.flashwork.domain.enums.ServiceStatus;
import com.rafaelaugustor.flashwork.domain.enums.WorkType;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.UUID;

@Repository
public interface ServiceRepository extends JpaRepository<Service, UUID> {
    Service findByIdAndClientEmail(UUID serviceId, String email);

    Page<Service> findByClientEmailAndStatus(String email, ServiceStatus status, Pageable pageable);

    Page<Service> findByCategoriesId(UUID categoryId, Pageable pageable);

    Page<Service> findByWorkType(WorkType workType, Pageable pageable);

    @Query("SELECT s FROM Service s JOIN Address a ON a.id = s.addressId WHERE a.city = :city AND s.client.email != :clientEmail")
    Page<Service> findByCityExcludingClient(@Param("city") String city, @Param("clientEmail") String clientEmail, Pageable pageable);


}