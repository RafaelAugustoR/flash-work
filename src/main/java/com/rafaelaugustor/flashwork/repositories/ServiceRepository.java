package com.rafaelaugustor.flashwork.repositories;

import com.rafaelaugustor.flashwork.domain.entities.Service;
import com.rafaelaugustor.flashwork.domain.enums.WorkType;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.UUID;

@Repository
public interface ServiceRepository extends JpaRepository<Service, UUID> {
    Service findByIdAndClientEmail(UUID serviceId, String email);

    Page<Service> findByClientEmail(String email, Pageable pageable);

    Page<Service> findByCategoriesId(UUID categoryId, Pageable pageable);

    Page<Service> findByWorkType(WorkType workType, Pageable pageable);

    @Query("SELECT s FROM Service s WHERE s.addressId = :addressId AND s.client.email <> :email")
    Page<Service> findByAddressIdExcludingClient(UUID addressId, String email, Pageable pageable);

}