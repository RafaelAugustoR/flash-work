package com.rafaelaugustor.flashwork.rest.dtos.request;

import com.rafaelaugustor.flashwork.domain.entities.User;
import com.rafaelaugustor.flashwork.domain.enums.ReviewType;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.UUID;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class ReviewRequestDTO {

    private Double rating;

    private String description;

    private UUID targetId;

    private ReviewType reviewType;

    private User reviewer;
}
