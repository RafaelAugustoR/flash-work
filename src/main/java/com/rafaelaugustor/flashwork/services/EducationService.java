package com.rafaelaugustor.flashwork.services;

import com.rafaelaugustor.flashwork.domain.entities.Education;
import com.rafaelaugustor.flashwork.domain.entities.User;
import com.rafaelaugustor.flashwork.repositories.EducationRepository;
import com.rafaelaugustor.flashwork.repositories.UserRepository;
import com.rafaelaugustor.flashwork.rest.dtos.request.EducationRequestDTO;
import com.rafaelaugustor.flashwork.rest.dtos.response.EducationResponseDTO;
import jakarta.persistence.EntityNotFoundException;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.security.Principal;
import java.util.List;
import java.util.UUID;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class EducationService {

    private final EducationRepository educationRepository;
    private final UserRepository userRepository;

    public void addEducation(EducationRequestDTO request, Principal principal) {
        User user = userRepository.findByEmail(principal.getName());

        Education educationToSave = Education.builder()
                .degreeType(request.getDegree())
                .yearOfCompletion(request.getYearOfCompletion())
                .institution(request.getInstitution())
                .user(user)
                .build();

        educationRepository.save(educationToSave);
    }

    @Transactional
    public List<EducationResponseDTO> getEducations(Principal principal) {
        User user = userRepository.findByEmail(principal.getName());
        return user.getEducation().stream()
                .map(education -> new EducationResponseDTO(education.getId(), education.getDegreeType(), education.getCourse(), education.getInstitution(), education.getYearOfCompletion(), education.getYearOfInitiation()))
                .collect(Collectors.toList());
    }

    public void updateEducation(UUID educationId, EducationRequestDTO request, Principal principal) {
        var foundedEducation = educationRepository.findByIdAndUserEmail(educationId, principal.getName())
                .orElseThrow(() -> new EntityNotFoundException("Education not found."));

        foundedEducation.setDegreeType(request.getDegree());
        foundedEducation.setYearOfCompletion(request.getYearOfCompletion());
        foundedEducation.setInstitution(request.getInstitution());
        foundedEducation.setYearOfInitiation(request.getYearOfInitiation());

        educationRepository.save(foundedEducation);
    }

    public EducationResponseDTO findEducationById(UUID educationId, Principal principal) {
        var foundedEducation = educationRepository.findByIdAndUserEmail(educationId, principal.getName())
                .orElseThrow(() -> new EntityNotFoundException("Education not found."));

        return EducationResponseDTO.builder()
                .yearOfCompletion(foundedEducation.getYearOfCompletion())
                .yearOfInitiation(foundedEducation.getYearOfInitiation())
                .institution(foundedEducation.getInstitution())
                .degree(foundedEducation.getDegreeType())
                .course(foundedEducation.getCourse())
                .build();
    }

    public void deleteEducation(UUID educationId, Principal principal) {
        var education = educationRepository.findByIdAndUserEmail(educationId, principal.getName())
                .orElseThrow(() -> new EntityNotFoundException("Education not found."));

        educationRepository.delete(education);

    }
}
