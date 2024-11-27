package com.rafaelaugustor.flashwork.rest.dtos.request;

import com.rafaelaugustor.flashwork.domain.entities.Category;
import com.rafaelaugustor.flashwork.domain.entities.Service;
import com.rafaelaugustor.flashwork.domain.enums.WorkType;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;
import java.util.List;
import java.util.UUID;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class ServiceRequestDTO {
    private String title;
    private String description;
    private String budget;
    private LocalDate deadline;
    private WorkType workType;
    private UUID addressId;
    private List<UUID> categories;

    public ServiceRequestDTO(Service service) {
        this.title = service.getTitle();
        this.description = service.getDescription();
        this.budget = service.getBudget();
        this.deadline = service.getDeadline();
        this.workType = service.getWorkType();
        this.addressId = service.getAddressId();
        for (Category category : service.getCategories()) {
            this.categories.add(category.getId());
        }
    }
}
