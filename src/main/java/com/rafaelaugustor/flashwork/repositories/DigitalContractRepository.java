package com.rafaelaugustor.flashwork.repositories;

import com.rafaelaugustor.flashwork.domain.entities.DigitalContract;
import com.rafaelaugustor.flashwork.domain.entities.User;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.UUID;

@Repository
public interface DigitalContractRepository extends JpaRepository<DigitalContract, UUID> {
    Page<DigitalContract> findByClientOrFreelancer(User client, User freelancer, Pageable pageable);
}
