package com.rafaelaugustor.flashwork.rest.dtos.response;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.sql.Timestamp;
import java.time.LocalDate;
import java.util.List;
import java.util.UUID;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class ServiceResponseDTO {
    private UUID id;
    private String title;
    private String description;
    private String budget;
    private String workType;
    private LocalDate deadline;
    private UUID addressId;
    private UserMinDTO freelancer;
    private Timestamp createdAt;
    private UserMinDTO client;
    private List<CategoryResponseDTO> categories;
    private Integer proposalQuantity;
}
