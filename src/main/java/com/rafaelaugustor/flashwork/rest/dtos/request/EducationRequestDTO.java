package com.rafaelaugustor.flashwork.rest.dtos.request;

import com.rafaelaugustor.flashwork.domain.enums.DegreeType;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class EducationRequestDTO {

    private DegreeType degree;

    private String institution;

    private String course;

    private LocalDate yearOfCompletion;

    private LocalDate yearOfInitiation;
}
