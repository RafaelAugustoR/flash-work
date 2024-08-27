package com.rafaelaugustor.flashwork.services;

import com.rafaelaugustor.flashwork.domain.entities.Review;
import com.rafaelaugustor.flashwork.domain.entities.User;
import com.rafaelaugustor.flashwork.domain.enums.ReviewType;
import com.rafaelaugustor.flashwork.repositories.ReviewRepository;
import com.rafaelaugustor.flashwork.repositories.ServiceRepository;
import com.rafaelaugustor.flashwork.repositories.UserRepository;
import com.rafaelaugustor.flashwork.rest.dtos.request.ReviewRequestDTO;
import com.rafaelaugustor.flashwork.rest.dtos.response.ReviewResponseDTO;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.security.Principal;
import java.util.List;
import java.util.UUID;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class ReviewService {

    private final ReviewRepository reviewRepository;

    private final UserRepository userRepository;

    private final ServiceRepository serviceRepository;

    public void createReview(ReviewRequestDTO request, Principal principal) {

        User user = userRepository.findByEmail(principal.getName());

        var reviewType = getReviewType(request);

        var reviewToSave = Review.builder()
                .reviewType(reviewType)
                .reviewer(user)
                .description(request.getDescription())
                .rating(request.getRating())
                .targetId(request.getTargetId())
                .build();

        reviewRepository.save(reviewToSave);
    }

    public List<ReviewResponseDTO> findReviewByTargetId(UUID targetId) {
        List<Review> reviews = reviewRepository.findByTargetId(targetId);
        return reviews.stream()
                .map(this::toResponseDTO)
                .collect(Collectors.toList());
    }

    public List<ReviewResponseDTO> findReviewsByReviewer(UUID reviewerId) {
        List<Review> reviews = reviewRepository.findByReviewerId(reviewerId);
        return reviews.stream()
                .map(this::toResponseDTO)
                .collect(Collectors.toList());
    }

    public void updateReview(UUID reviewId, ReviewRequestDTO request, Principal principal) {

        var review = reviewRepository.findByIdAndReviewerEmail(reviewId, principal.getName());

        review.setDescription(request.getDescription());
        review.setRating(request.getRating());

        reviewRepository.save(review);
    }

    public void deleteReview(UUID reviewId, Principal principal) {
        var review = reviewRepository.findByIdAndReviewerEmail(reviewId, principal.getName());
        
        reviewRepository.delete(review);
    }

    private ReviewResponseDTO toResponseDTO(Review review) {
        return ReviewResponseDTO.builder()
                .id(review.getId())
                .reviewerName(review.getReviewer().getName())
                .description(review.getDescription())
                .rating(review.getRating())
                .createdAt(review.getCreatedAt())
                .targetId(review.getTargetId())
                .reviewType(review.getReviewType())
                .build();
    }

    private ReviewType getReviewType(ReviewRequestDTO request) {
        if (serviceRepository.findById(request.getTargetId()).isPresent()) {
            return ReviewType.SERVICE;
        } else if (userRepository.findById(request.getTargetId()).isPresent()) {
            return ReviewType.USER;
        } else {
            throw new RuntimeException("Target id not found");
        }
    }
}
