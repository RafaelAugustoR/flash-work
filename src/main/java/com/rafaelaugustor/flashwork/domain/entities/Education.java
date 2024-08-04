package com.rafaelaugustor.flashwork.domain.entities;

import com.rafaelaugustor.flashwork.domain.enums.DegreeType;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.CreationTimestamp;

import java.sql.Timestamp;
import java.util.Date;
import java.util.UUID;

@Entity
@AllArgsConstructor
@NoArgsConstructor
@Data
public class Education {

    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    private UUID id;

    private DegreeType degreeType;

    private Date yearOfCompletion;

    private String institution;

    @ManyToOne
    @JoinColumn(name = "user_id")
    private User user;

    @CreationTimestamp
    private Timestamp createdAt;
}
