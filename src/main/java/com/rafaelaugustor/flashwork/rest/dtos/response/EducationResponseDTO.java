package com.rafaelaugustor.flashwork.rest.dtos.response;

import com.rafaelaugustor.flashwork.domain.enums.DegreeType;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;
import java.util.UUID;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class EducationResponseDTO {

    private UUID id;

    private DegreeType degree;

    private String institution;

    private String course;

    private LocalDate yearOfCompletion;

    private LocalDate yearOfInitiation;
}
