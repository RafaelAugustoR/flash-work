package com.rafaelaugustor.flashwork.rest.controllers;

import com.rafaelaugustor.flashwork.rest.dtos.request.ReviewRequestDTO;
import com.rafaelaugustor.flashwork.rest.dtos.response.ReviewResponseDTO;
import com.rafaelaugustor.flashwork.services.ReviewService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.security.Principal;
import java.util.List;
import java.util.UUID;

import static com.rafaelaugustor.flashwork.utils.Constants.APP_ROOT;

@RestController
@RequestMapping(APP_ROOT + "/review")
@RequiredArgsConstructor
public class ReviewController {

    private final ReviewService reviewService;

    @PostMapping
    public ResponseEntity<Void> createReview(@RequestBody ReviewRequestDTO request, Principal principal) {
        reviewService.createReview(request, principal);
        return ResponseEntity.ok().build();
    }

    @GetMapping("/{id}")
    public ResponseEntity<List<ReviewResponseDTO>> findReviewByTargetId(@PathVariable UUID id) {
        return ResponseEntity.ok(reviewService.findReviewByTargetId(id));
    }

    @GetMapping("/{id}")
    public ResponseEntity<List<ReviewResponseDTO>> findReviewsByReviewer(@PathVariable UUID id){
        return ResponseEntity.ok(reviewService.findReviewsByReviewer(id));
    }

    @PutMapping("/update/{id}")
    public ResponseEntity<Void> updateReview(@RequestBody ReviewRequestDTO request, @PathVariable UUID id, Principal principal) {
        reviewService.updateReview(id, request, principal);
        return ResponseEntity.ok().build();
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteReview(@PathVariable UUID id, Principal principal) {
        reviewService.deleteReview(id, principal);
        return ResponseEntity.ok().build();
    }
}
