package com.rafaelaugustor.flashwork.domain.entities;

import com.rafaelaugustor.flashwork.domain.enums.UserRole;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import java.sql.Timestamp;
import java.time.LocalDate;
import java.util.Collection;
import java.util.List;
import java.util.UUID;

@Entity
@AllArgsConstructor
@NoArgsConstructor
@Data
@Builder
@Table(name = "users")
public class User implements UserDetails {

    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    private UUID id;

    @Column(nullable = false, length = 150)
    private String name;

    @Column(nullable = false, updatable = false, unique = true, length = 80)
    private String email;

    @Column(nullable = false)
    private String password;

    @Column(nullable = true, unique = true, length = 14, updatable = false)
    private String cpf;

    @Column(nullable = true, unique = true, length = 15)
    private String phone;

    @Column(nullable = true, length = 50)
    private String profession;

    @Column(nullable = true, columnDefinition = "TEXT")
    private String description;

    @Column(nullable = true)
    private String profilePicture;

    @Column(nullable = true)
    private LocalDate birthDate;

    @Enumerated(EnumType.STRING)
    @Column(nullable = false)
    private UserRole role;

    @CreationTimestamp
    private Timestamp createdAt;

    @UpdateTimestamp
    private Timestamp updatedAt;

    @OneToMany(mappedBy = "user")
    private List<Education> education;

    @OneToMany(mappedBy = "provider")
    private List<Service> services;

    @OneToMany(mappedBy = "reviewer")
    private List<Review> reviews;

    @OneToMany(mappedBy = "sender")
    private List<Notification> sentNotifications;

    @OneToMany(mappedBy = "receiver")
    private List<Notification> receivedNotifications;

    @OneToMany(mappedBy = "requester")
    private List<ServiceRequest> serviceRequests;

    @OneToMany(mappedBy = "sender")
    private List<Message> sentMessages;

    @OneToMany(mappedBy = "receiver")
    private List<Message> receivedMessages;

    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        return List.of(new SimpleGrantedAuthority("ROLE_" + role));
    }

    @Override
    public String getUsername() {
        return email;
    }

    @Override
    public boolean isAccountNonExpired() {
        return true;
    }

    @Override
    public boolean isAccountNonLocked() {
        return true;
    }

    @Override
    public boolean isCredentialsNonExpired() {
        return true;
    }

    @Override
    public boolean isEnabled() {
        return true;
    }
}
