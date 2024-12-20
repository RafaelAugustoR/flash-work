package com.rafaelaugustor.flashwork.domain.entities;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.rafaelaugustor.flashwork.domain.enums.UserRole;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;
import org.hibernate.proxy.HibernateProxy;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import java.sql.Timestamp;
import java.time.LocalDate;
import java.util.Collection;
import java.util.List;
import java.util.Objects;
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
    private String profileImage;

    @Column(nullable = true)
    private LocalDate birthDate;

    @Enumerated(EnumType.STRING)
    @Column(nullable = false)
    private UserRole role;

    @CreationTimestamp
    private Timestamp createdAt;

    @UpdateTimestamp
    private Timestamp updatedAt;

    @JsonIgnore
    @OneToMany(mappedBy = "user")
    private List<Education> education;

    @JsonIgnore
    @OneToMany(mappedBy = "client")
    private List<Service> services;

    @JsonIgnore
    @OneToMany(mappedBy = "reviewer")
    private List<Review> reviews;

    @JsonIgnore
    @OneToMany(mappedBy = "sender")
    private List<Notification> sentNotifications;

    @JsonIgnore
    @OneToMany(mappedBy = "receiver")
    private List<Notification> receivedNotifications;

    @JsonIgnore
    @OneToMany(mappedBy = "freelancer")
    private List<Proposal> proposals;

    @JsonIgnore
    @OneToMany(mappedBy = "user")
    private List<Message> sentMessages;

    @JsonIgnore
    @OneToMany(mappedBy = "user", fetch = FetchType.EAGER)
    private List<Address> addresses;

    @JsonIgnore
    @ManyToMany(mappedBy = "users", fetch = FetchType.EAGER)
    private List<Chat> chats;

    @JsonIgnore
    @OneToOne(mappedBy = "user", cascade = CascadeType.ALL)
    private Wallet wallet;

    @JsonIgnore
    @OneToMany(mappedBy = "freelancer")
    private List<DigitalContract> freelancerContracts;

    @JsonIgnore
    @OneToMany(mappedBy = "client")
    private List<DigitalContract> clientContracts;

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

    @Override
    public final boolean equals(Object o) {
        if (this == o) return true;
        if (o == null) return false;
        Class<?> oEffectiveClass = o instanceof HibernateProxy ? ((HibernateProxy) o).getHibernateLazyInitializer().getPersistentClass() : o.getClass();
        Class<?> thisEffectiveClass = this instanceof HibernateProxy ? ((HibernateProxy) this).getHibernateLazyInitializer().getPersistentClass() : this.getClass();
        if (thisEffectiveClass != oEffectiveClass) return false;
        User user = (User) o;
        return getId() != null && Objects.equals(getId(), user.getId());
    }

    @Override
    public final int hashCode() {
        return this instanceof HibernateProxy ? ((HibernateProxy) this).getHibernateLazyInitializer().getPersistentClass().hashCode() : getClass().hashCode();
    }
}
