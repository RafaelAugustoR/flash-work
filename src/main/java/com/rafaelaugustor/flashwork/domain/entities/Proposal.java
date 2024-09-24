package com.rafaelaugustor.flashwork.domain.entities;

import com.rafaelaugustor.flashwork.domain.enums.ProposalStatus;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.sql.Timestamp;
import java.time.LocalDate;
import java.util.UUID;

@Entity
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class Proposal {

    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    private UUID id;

    @ManyToOne
    @JoinColumn(name = "service_id", nullable = false)
    private Service service;

    @ManyToOne
    @JoinColumn(name = "freelancer_id", nullable = false)
    private User freelancer;

    @Enumerated(EnumType.STRING)
    @Column(nullable = false)
    private ProposalStatus status;

    @Column(nullable = false)
    private Double offerAmount;

    @Column(nullable = false)
    private LocalDate estimatedCompletionTime;

    @Column(columnDefinition = "TEXT")
    private String message  ;

    @Column(nullable = false)
    private Timestamp requestedAt;

    @Column(nullable = true)
    private Timestamp completedAt;
}
