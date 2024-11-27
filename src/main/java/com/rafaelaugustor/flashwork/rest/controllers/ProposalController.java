package com.rafaelaugustor.flashwork.rest.controllers;

import com.rafaelaugustor.flashwork.domain.enums.ProposalStatus;
import com.rafaelaugustor.flashwork.rest.dtos.request.ProposalRequestDTO;
import com.rafaelaugustor.flashwork.rest.dtos.response.ProposalResponseDTO;
import com.rafaelaugustor.flashwork.services.ProposalService;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.security.Principal;
import java.util.UUID;

import static com.rafaelaugustor.flashwork.utils.Constants.APP_ROOT;

@RestController
@RequestMapping(APP_ROOT + "/proposals")
@RequiredArgsConstructor
@CrossOrigin
public class ProposalController {

    private final ProposalService proposalService;

    @PostMapping
    public ResponseEntity<ProposalResponseDTO> createProposal(@RequestBody ProposalRequestDTO request, Principal principal) {
        var proposal = proposalService.create(request, principal);
        return ResponseEntity.ok().body(proposal);
    }

    @PostMapping("/{proposalId}/respond")
    public ResponseEntity<ProposalResponseDTO> respondToProposal(
            @PathVariable UUID proposalId,
            @RequestParam ProposalStatus status,
            Principal principal) {
        ProposalResponseDTO response = proposalService.respondToProposal(proposalId, status, principal);
        return ResponseEntity.ok(response);
    }

    @PatchMapping("/{proposalId}/accept")
    public ResponseEntity<Void> acceptProposal(
            @PathVariable UUID proposalId,
            Principal principal,
            Pageable pageable) {
        proposalService.acceptProposal(proposalId, principal, pageable);
        return ResponseEntity.ok().build();
    }

    @GetMapping("/user")
    public ResponseEntity<Page<ProposalResponseDTO>> findAllByUser(Principal principal, Pageable pageable) {
        var proposals = proposalService.findAllByUser(principal, pageable);

        return ResponseEntity.ok().body(proposals);
    }

    @GetMapping("/service/{id}")
    public ResponseEntity<Page<ProposalResponseDTO>> findAllByService(@PathVariable UUID id, Pageable pageable) {
        var proposals = proposalService.findAllByService(id, pageable);

        return ResponseEntity.ok().body(proposals);
    }

    @PutMapping("/{id}/status")
    public ResponseEntity<ProposalResponseDTO> updateProposalStatus(@PathVariable UUID id, @RequestParam ProposalStatus status, Principal principal) {
        var proposal = proposalService.update(id, status, principal);
        return ResponseEntity.ok().body(proposal);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> cancelProposal(@PathVariable UUID id, Principal principal) {
        proposalService.cancelProposal(id, principal);
        return ResponseEntity.ok().build();
    }
}
