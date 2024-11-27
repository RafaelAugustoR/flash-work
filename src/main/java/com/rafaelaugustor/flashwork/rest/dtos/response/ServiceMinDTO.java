package com.rafaelaugustor.flashwork.rest.dtos.response;

import com.rafaelaugustor.flashwork.domain.entities.Service;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class ServiceMinDTO {

    private String title;
    private String description;
    private String budget;

    public ServiceMinDTO(Service service) {
        this.title = service.getTitle();
        this.description = service.getDescription();
        this.budget = service.getBudget();
    }
}
