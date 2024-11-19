package com.rafaelaugustor.flashwork.repositories;

import com.rafaelaugustor.flashwork.domain.entities.Notification;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.UUID;

@Repository
public interface NotificationRepository extends JpaRepository<Notification, UUID> {
    Page<Notification> findByReceiverId(UUID userId, Pageable pageable);
}