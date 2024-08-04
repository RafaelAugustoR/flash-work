package com.rafaelaugustor.flashwork.domain.entities;

import com.rafaelaugustor.flashwork.domain.enums.UserRole;
import jakarta.persistence.*;
import lombok.*;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;

import java.sql.Timestamp;
import java.util.Date;
import java.util.List;
import java.util.UUID;

@Entity
@AllArgsConstructor
@NoArgsConstructor
@Data
@Table(name = "users")
public class User {

    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    private UUID id;

    private String name;

    private String email;

    private String password;

    private String cpf;

    private String phone;

    private String profession;

    private String description;

    private String profilePicture;

    private Date birthDate;

    @Enumerated(EnumType.STRING)
    private UserRole role;

    @OneToMany(mappedBy = "user")
    private List<Education> education;

    @OneToMany(mappedBy = "provider")
    private List<Service> services;

    @OneToMany(mappedBy = "reviewer")
    private List<Review> reviews;

    @CreationTimestamp
    private Timestamp createdAt;

    @UpdateTimestamp
    private Timestamp updatedAt;

    @OneToMany(mappedBy = "sender")
    private List<Notification> sentNotifications;

    @OneToMany(mappedBy = "receiver")
    private List<Notification> receivedNotifications;
}