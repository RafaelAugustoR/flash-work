package com.rafaelaugustor.flashwork.rest.dtos.response;

import com.rafaelaugustor.flashwork.domain.entities.User;
import com.rafaelaugustor.flashwork.domain.enums.ReviewType;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.sql.Timestamp;
import java.util.UUID;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class ReviewResponseDTO {

    private UUID id;

    private Double rating;

    private String description;

    private Timestamp createdAt;

    private ReviewType reviewType;

    private UUID targetId;

    private String reviewerName;
}
