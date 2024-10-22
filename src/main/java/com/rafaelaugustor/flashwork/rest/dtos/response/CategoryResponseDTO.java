package com.rafaelaugustor.flashwork.rest.dtos.response;

import com.rafaelaugustor.flashwork.domain.entities.Category;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.UUID;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class CategoryResponseDTO {
    private UUID id;
    private String name;
    private String description;
    private String iconName;

    public CategoryResponseDTO(Category entity) {
        this.id = entity.getId();
        this.name = entity.getName();
        this.iconName = entity.getIconName();
        this.description = entity.getDescription();
    }
}
