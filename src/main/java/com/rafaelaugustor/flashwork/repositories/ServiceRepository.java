package com.rafaelaugustor.flashwork.repositories;

import com.rafaelaugustor.flashwork.domain.entities.Service;
import com.rafaelaugustor.flashwork.domain.enums.WorkType;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.UUID;

@Repository
public interface ServiceRepository extends JpaRepository<Service, UUID> {
    Service findByIdAndClientEmail(UUID serviceId, String email);

    List<Service> findByClientEmail(String email);

    List<Service> findByCategoriesId(UUID categoryId);

    List<Service> findByWorkType(WorkType workType);

    @Query("SELECT s FROM Service s WHERE LOWER(s.location) = LOWER(:location)")
    List<Service> findByLocationExcludingClient(@Param("location") String location);
}