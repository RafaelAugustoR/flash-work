package com.rafaelaugustor.flashwork.services;

import com.rafaelaugustor.flashwork.domain.entities.Category;
import com.rafaelaugustor.flashwork.repositories.CategoryRepository;
import com.rafaelaugustor.flashwork.rest.dtos.request.CategoryRequestDTO;
import com.rafaelaugustor.flashwork.rest.dtos.response.CategoryResponseDTO;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.UUID;

@Service
@RequiredArgsConstructor
public class CategoryService {

    private final CategoryRepository categoryRepository;

    public CategoryResponseDTO create(CategoryRequestDTO request) {
        Category category = Category.builder()
                .name(request.getName())
                .description(request.getDescription())
                .iconName(request.getIconName())
                .build();
        Category categoryToSave = categoryRepository.save(category);

        return CategoryResponseDTO.builder()
                .id(categoryToSave.getId())
                .name(categoryToSave.getName())
                .build();
    }

    public CategoryResponseDTO update(UUID id, CategoryRequestDTO request) {
        Category category = categoryRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Category not found"));

        category.setName(request.getName());
        Category updatedCategory = categoryRepository.save(category);

        return CategoryResponseDTO.builder()
                .id(updatedCategory.getId())
                .name(updatedCategory.getName())
                .description(category.getDescription())
                .iconName(category.getIconName())
                .build();
    }

    public void delete(UUID id) {
        categoryRepository.deleteById(id);
    }

    public CategoryResponseDTO findById(UUID id) {
        Category category = categoryRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Category not found"));

        return CategoryResponseDTO.builder()
                .id(category.getId())
                .name(category.getName())
                .description(category.getDescription())
                .iconName(category.getIconName())
                .build();
    }

    public Page<CategoryResponseDTO> findAll(Pageable pageable) {
        return categoryRepository.findAll(pageable).map(category -> CategoryResponseDTO.builder()
                        .id(category.getId())
                        .name(category.getName())
                        .iconName(category.getIconName())
                        .description(category.getDescription())
                        .build());
    }
}
