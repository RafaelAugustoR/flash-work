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
@CrossOrigin
public class EducationController {

    private final EducationService educationService;

    @PostMapping
    public ResponseEntity<EducationResponseDTO> createEducation(@RequestBody EducationRequestDTO request, Principal principal) {
        educationService.create(request, principal);
        return ResponseEntity.ok().build();
    }

    @GetMapping
    public ResponseEntity<List<EducationResponseDTO>> findAllEducations(Principal principal) {
        var educations = educationService.findAll(principal);
        return ResponseEntity.ok().body(educations);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteEducation(@PathVariable UUID id, Principal principal) {
        educationService.delete(id, principal);
        return ResponseEntity.noContent().build();
    }

    @PutMapping("/{id}")
    public ResponseEntity<Void> updateEducation(@RequestBody EducationRequestDTO request, @PathVariable UUID id, Principal principal) {
        educationService.update(id, request, principal);
        return ResponseEntity.ok().build();
    }

    @GetMapping("/{id}")
    public ResponseEntity<EducationResponseDTO> findEducationById(@PathVariable UUID id, Principal principal) {
        return ResponseEntity.ok().body(educationService.findById(id, principal));
    }
}
