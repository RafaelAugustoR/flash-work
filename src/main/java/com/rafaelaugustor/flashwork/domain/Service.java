package com.rafaelaugustor.flashwork.domain;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.CreationTimestamp;

import java.sql.Timestamp;
import java.util.UUID;

@Entity
@AllArgsConstructor
@NoArgsConstructor
@Data
public class Service {

    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    private UUID id;

    private String title;

    private String description;

    private Double price;

    private String workType;

    private String location;

    @ManyToOne
    @JoinColumn(name = "provider_id")
    private User provider;

    @CreationTimestamp
    private Timestamp createdAt;
}
