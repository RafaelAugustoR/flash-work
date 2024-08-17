package com.rafaelaugustor.flashwork.rest.controllers;

import com.rafaelaugustor.flashwork.rest.dtos.request.EducationRequestDTO;
import com.rafaelaugustor.flashwork.rest.dtos.response.EducationResponseDTO;
import com.rafaelaugustor.flashwork.services.EducationService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.security.Principal;
import java.util.List;
import java.util.UUID;

import static com.rafaelaugustor.flashwork.utils.Constants.APP_ROOT;

@RestController
@RequestMapping(APP_ROOT + "/educations")
@RequiredArgsConstructor
public class EducationController {

    private final EducationService educationService;

    @PostMapping
    public ResponseEntity<EducationResponseDTO> addEducation(@RequestBody EducationRequestDTO request, Principal principal) {
        educationService.addEducation(request, principal);
        return ResponseEntity.ok().build();
    }

    @GetMapping
    public ResponseEntity<List<EducationResponseDTO>> getEducations(Principal principal) {
        var educations = educationService.getEducations(principal);
        return ResponseEntity.ok().body(educations);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteEducation(@PathVariable UUID id, Principal principal) {
        educationService.deleteEducation(id, principal);
        return ResponseEntity.noContent().build();
    }

    @PutMapping("/update/{id}")
    public ResponseEntity<Void> updateEducation(@RequestBody EducationRequestDTO request, @PathVariable UUID id, Principal principal) {
        educationService.updateEducation(id, request, principal);
        return ResponseEntity.ok().build();
    }

    @GetMapping("/{id}")
    public ResponseEntity<EducationResponseDTO> getEducation(@PathVariable UUID id, Principal principal) {
        return ResponseEntity.ok().body(educationService.findEducationById(id, principal));
    }
}
