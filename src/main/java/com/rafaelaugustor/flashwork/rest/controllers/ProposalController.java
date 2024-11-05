package com.rafaelaugustor.flashwork.rest.controllers;

import com.rafaelaugustor.flashwork.domain.enums.ProposalStatus;
import com.rafaelaugustor.flashwork.rest.dtos.request.ProposalRequestDTO;
import com.rafaelaugustor.flashwork.rest.dtos.response.ProposalResponseDTO;
import com.rafaelaugustor.flashwork.services.ProposalService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.security.Principal;
import java.util.List;
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

    @GetMapping("/user")
    public ResponseEntity<List<ProposalResponseDTO>> findAllByUser(Principal principal){
        var proposals = proposalService.findAllByUser(principal);

        return ResponseEntity.ok().body(proposals);
    }

    @GetMapping("/service/{id}")
    public ResponseEntity<List<ProposalResponseDTO>> findAllByService(@PathVariable UUID id){
        var proposals = proposalService.findAllByService(id);

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
