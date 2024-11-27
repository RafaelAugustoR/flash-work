package com.rafaelaugustor.flashwork.rest.dtos.response;

import com.rafaelaugustor.flashwork.domain.entities.DigitalContract;
import com.rafaelaugustor.flashwork.domain.enums.ContractStatus;
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
public class DigitalContractResponseDTO {

    private UUID id;

    private ServiceMinDTO service;

    private UserMinDTO freelancer;

    private UserMinDTO client;

    private String cloudUrl;

    private ContractStatus status;

    private boolean signedByClient;

    private boolean signedByFreelancer;

    private LocalDate clientSignedAt;

    private LocalDate freelancerSignedAt;

    public DigitalContractResponseDTO(DigitalContract digitalContract){
        this.id = digitalContract.getId();
        this.service = new ServiceMinDTO(digitalContract.getService());
        this.freelancer = new UserMinDTO(digitalContract.getFreelancer());
        this.client = new UserMinDTO(digitalContract.getClient());
        this.cloudUrl = digitalContract.getCloudUrl();
        this.status = digitalContract.getStatus();
        this.signedByClient = digitalContract.isSignedByClient();
        this.signedByFreelancer = digitalContract.isSignedByFreelancer();
        this.clientSignedAt = digitalContract.getClientSignedAt();
        this.freelancerSignedAt = digitalContract.getFreelancerSignedAt();
    }

}
