package com.rafaelaugustor.flashwork.domain.entities;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;
import java.util.UUID;

@Entity
@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class DigitalContract {

    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    private UUID id;

    @OneToOne
    @JoinColumn(name = "service_id", nullable = false)
    private Service service;

    @ManyToOne
    @JoinColumn(name = "freelancer_id", nullable = false)
    private User freelancer;

    @ManyToOne
    @JoinColumn(name = "client_id", nullable = false)
    private User client;

    @Lob
    private String terms;

    private LocalDateTime createdAt;

    private boolean isSignedByClient;
    private boolean isSignedByFreelancer;

}