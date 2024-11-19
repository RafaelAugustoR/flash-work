package com.rafaelaugustor.flashwork.repositories;

import com.rafaelaugustor.flashwork.domain.entities.Proposal;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.UUID;

@Repository
public interface ProposalRepository extends JpaRepository<Proposal, UUID> {

    Page<Proposal> findAllByFreelancerEmail(String email, Pageable pageable);

    Page<Proposal> findAllByServiceId(UUID serviceId, Pageable pageable);

    Integer countByServiceId(UUID serviceId);
}
