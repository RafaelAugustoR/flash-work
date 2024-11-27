package com.rafaelaugustor.flashwork.services;

import com.rafaelaugustor.flashwork.domain.entities.Proposal;
import com.rafaelaugustor.flashwork.domain.enums.ProposalStatus;
import com.rafaelaugustor.flashwork.domain.enums.ServiceStatus;
import com.rafaelaugustor.flashwork.repositories.ProposalRepository;
import com.rafaelaugustor.flashwork.repositories.ServiceRepository;
import com.rafaelaugustor.flashwork.repositories.UserRepository;
import com.rafaelaugustor.flashwork.rest.dtos.request.DigitalContractRequestDTO;
import com.rafaelaugustor.flashwork.rest.dtos.request.NotificationRequestDTO;
import com.rafaelaugustor.flashwork.rest.dtos.request.ProposalRequestDTO;
import com.rafaelaugustor.flashwork.rest.dtos.request.ServiceRequestDTO;
import com.rafaelaugustor.flashwork.rest.dtos.response.ProposalResponseDTO;
import com.rafaelaugustor.flashwork.rest.dtos.response.UserMinDTO;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.security.Principal;
import java.sql.Timestamp;
import java.time.Instant;
import java.util.UUID;

@Service
@RequiredArgsConstructor
public class ProposalService {

    private final ProposalRepository proposalRepository;
    private final ServiceRepository serviceRepository;
    private final UserRepository userRepository;
    private final NotificationService notificationService;
    private final DigitalContractService digitalContractService;

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

    public void acceptProposal(UUID proposalId, Principal principal, Pageable pageable) {

        var user = userRepository.findByEmail(principal.getName());

        var proposal = proposalRepository.findById(proposalId)
                .orElseThrow(() -> new IllegalArgumentException("Proposal not found"));

        var service = proposal.getService();
        if (!service.getClient().getEmail().equals(principal.getName())) {
            throw new SecurityException("User is not authorized to accept this proposal");
        }

        proposal.setStatus(ProposalStatus.ACCEPTED);
        proposalRepository.save(proposal);

        var otherProposals = proposalRepository.findAllByServiceId(service.getId(), pageable).stream()
                .filter(p -> !p.getId().equals(proposalId))
                .toList();

        otherProposals.forEach(p -> {
            p.setStatus(ProposalStatus.REJECTED);
            proposalRepository.save(p);

            String rejectedMessage = String.format(
                    "Olá %s, sua proposta para o serviço '%s' foi recusada. " +
                            "Agradecemos por seu interesse e esperamos que participe de futuras oportunidades!",
                    p.getFreelancer().getName(),
                    service.getTitle()
            );

            notificationService.sendNotification(new NotificationRequestDTO(
                    rejectedMessage,
                    new UserMinDTO(p.getFreelancer()),
                    new UserMinDTO(service.getClient())
            ));
        });

        service.setStatus(ServiceStatus.IN_PROGRESS);
        service.setFreelancer(proposal.getFreelancer());
        serviceRepository.save(service);

        String acceptedMessage = String.format(
                "Olá %s, sua proposta para o serviço '%s' foi aceita! " +
                        "O serviço agora está em andamento. Parabéns e bom trabalho!",
                proposal.getFreelancer().getName(),
                service.getTitle()
        );

        notificationService.sendNotification(new NotificationRequestDTO(
                acceptedMessage,
                new UserMinDTO(proposal.getFreelancer()),
                new UserMinDTO(service.getClient())
        ));

        DigitalContractRequestDTO contractRequest = DigitalContractRequestDTO.builder()
                .clientId(service.getClient().getId())
                .freelancerId(service.getFreelancer().getId())
                .service(new ServiceRequestDTO(service))
                .build();
        try {
            digitalContractService.generateDocument(contractRequest, service.getId());
        } catch (Exception e) {
            throw new RuntimeException("Erro ao gerar o contrato", e);
        }
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

    public Page<ProposalResponseDTO> findAllByService(UUID serviceId, Pageable pageable) {

        Page<Proposal> proposals = proposalRepository.findAllByServiceId(serviceId, pageable);

        return proposals.map(this::toResponseDTO);
    }

    public Page<ProposalResponseDTO> findAllByUser(Principal principal, Pageable pageable) {

        Page<Proposal> proposals = proposalRepository.findAllByFreelancerEmail(principal.getName(), pageable);

        return proposals.map(this::toResponseDTO);
    }

    public void cancelProposal(UUID proposalId, Principal principal) {

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
