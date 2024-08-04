package com.rafaelaugustor.flashwork.repositories;

import com.rafaelaugustor.flashwork.domain.entities.CategoryService;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.UUID;

public interface CategoryServiceRepository extends JpaRepository<CategoryService, UUID> {
}