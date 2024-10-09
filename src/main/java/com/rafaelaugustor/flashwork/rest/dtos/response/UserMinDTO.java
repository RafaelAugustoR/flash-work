package com.rafaelaugustor.flashwork.rest.dtos.response;

import com.rafaelaugustor.flashwork.domain.entities.User;
import lombok.AllArgsConstructor;
import lombok.Getter;

import java.util.UUID;

@Getter
@AllArgsConstructor
public class UserMinDTO {

    private UUID id;

    private String name;

    private String profilePicture;

    private String description;

    public UserMinDTO(User entity) {
        this.id = entity.getId();
        this.name = entity.getName();
        this.profilePicture = entity.getProfilePicture();
        this.description = entity.getDescription();
    }
}
