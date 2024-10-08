package com.rafaelaugustor.flashwork.repositories;

import com.rafaelaugustor.flashwork.domain.entities.Review;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.UUID;

@Repository
public interface ReviewRepository extends JpaRepository<Review, UUID> {
    List<Review> findByTargetId(UUID targetId);

    Review findByIdAndReviewerEmail(UUID reviewId, String email);

    List<Review> findByReviewerId(UUID reviewerId);
}