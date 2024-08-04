package com.rafaelaugustor.flashwork.domain.entities;

import com.rafaelaugustor.flashwork.domain.enums.WorkType;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.CreationTimestamp;

import java.sql.Timestamp;
import java.util.HashSet;
import java.util.Set;
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

    @Enumerated(EnumType.STRING)
    private WorkType workType;

    private String location;

    @ManyToOne
    @JoinColumn(name = "provider_id")
    private User provider;

    @ManyToMany(mappedBy = "services")
    private Set<Category> categories = new HashSet<>();

    @CreationTimestamp
    private Timestamp createdAt;
}
