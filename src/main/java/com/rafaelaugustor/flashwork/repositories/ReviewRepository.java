package com.rafaelaugustor.flashwork.repositories;

import com.rafaelaugustor.flashwork.domain.entities.Review;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.UUID;

public interface ReviewRepository extends JpaRepository<Review, UUID> {
    List<Review> findByTargetId(UUID targetId);

    Review findByIdAndReviewerEmail(UUID reviewId, String email);

    List<Review> findByReviewerId(UUID reviewerId);
}