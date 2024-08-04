package com.rafaelaugustor.flashwork.repositories;

import com.rafaelaugustor.flashwork.domain.entities.Education;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.UUID;

public interface EducationRepository extends JpaRepository<Education, UUID> {
}