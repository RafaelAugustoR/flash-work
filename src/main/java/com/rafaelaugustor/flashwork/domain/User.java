package com.rafaelaugustor.flashwork.domain;

import jakarta.persistence.*;
import lombok.*;

import java.util.Date;
import java.util.List;
import java.util.UUID;

@Entity
@AllArgsConstructor
@NoArgsConstructor
@Data
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

    @OneToMany(mappedBy = "user")
    private List<Education> education;
}
