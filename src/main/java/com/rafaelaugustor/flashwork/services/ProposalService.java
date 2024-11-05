package com.rafaelaugustor.flashwork.services;

import com.rafaelaugustor.flashwork.domain.entities.Proposal;
import com.rafaelaugustor.flashwork.domain.enums.ProposalStatus;
import com.rafaelaugustor.flashwork.repositories.ServiceRepository;
import com.rafaelaugustor.flashwork.repositories.ProposalRepository;
import com.rafaelaugustor.flashwork.repositories.UserRepository;
import com.rafaelaugustor.flashwork.rest.dtos.request.ProposalRequestDTO;
import com.rafaelaugustor.flashwork.rest.dtos.response.ProposalResponseDTO;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.security.Principal;
import java.sql.Timestamp;
import java.time.Instant;
import java.util.List;
import java.util.UUID;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class ProposalService {

    private final ProposalRepository proposalRepository;
    private final ServiceRepository serviceRepository;
    private final UserRepository userRepository;

    public ProposalResponseDTO create(ProposalRequestDTO request, Principal principal) {
        var user = userRepository.findByEmail(principal.getName());

        var service = serviceRepository.findById(request.getServiceId())
                .orElseThrow(() -> new IllegalArgumentException("Service not found"));

        var proposal = Proposal.builder()
                .service(service)
                .freelancer(user)
                .status(ProposalStatus.PENDING)
                .offerAmount(request.getOfferAmount())
                .message(request.getMessage())
                .requestedAt(Timestamp.from(Instant.now()))
                .estimatedCompletionTime(request.getEstimatedCompletionTime())
                .build();

        proposalRepository.save(proposal);

        return toResponseDTO(proposal);
    }

    public ProposalResponseDTO update(UUID proposalId, ProposalStatus status, Principal principal) {
        var proposal = proposalRepository.findById(proposalId)
                .orElseThrow(() -> new IllegalArgumentException("Proposal not found"));

        if (!proposal.getFreelancer().getEmail().equals(principal.getName())) {
            throw new SecurityException("User is not authorized to update this proposal");
        }

        proposal.setStatus(status);

        proposalRepository.save(proposal);

        return toResponseDTO(proposal);
    }

    public ProposalResponseDTO respondToProposal(UUID proposalId, ProposalStatus status, Principal principal) {
        var proposal = proposalRepository.findById(proposalId)
                .orElseThrow(() -> new IllegalArgumentException("Proposal not found"));

        var service = proposal.getService();
        var client = service.getClient();

        if (!client.getEmail().equals(principal.getName())) {
            throw new SecurityException("User is not authorized to respond to this proposal");
        }

        proposal.setStatus(status);
        proposalRepository.save(proposal);

        return toResponseDTO(proposal);
    }

    public List<ProposalResponseDTO> findAllByService(UUID serviceId){

        List<Proposal> proposals = proposalRepository.findAllByServiceId(serviceId);

        return proposals.stream()
                .map(this::toResponseDTO)
                .collect(Collectors.toList());
    }

    public List<ProposalResponseDTO> findAllByUser(Principal principal){

        List<Proposal> proposals = proposalRepository.findAllByFreelancerEmail(principal.getName());

        return proposals.stream()
                .map(this::toResponseDTO)
                .collect(Collectors.toList());
    }

    public void cancelProposal(UUID proposalId, Principal principal){

        var proposal = proposalRepository.findById(proposalId).orElseThrow();

        var client = proposal.getService().getClient();

        if (!client.getEmail().equals(principal.getName())) {
            throw new SecurityException("User is not authorized to cancel this proposal");
        }

        proposalRepository.delete(proposal);
    }

    private ProposalResponseDTO toResponseDTO(Proposal proposal) {
        return ProposalResponseDTO.builder()
                .id(proposal.getId())
                .serviceId(proposal.getService().getId())
                .freelancerId(proposal.getFreelancer().getId())
                .offerAmount(proposal.getOfferAmount())
                .status(proposal.getStatus())
                .message(proposal.getMessage())
                .requestedAt(proposal.getRequestedAt())
                .completedAt(proposal.getCompletedAt())
                .estimatedCompletionTime(proposal.getEstimatedCompletionTime())
                .build();
    }
}
