package fr.fms.apitrainings.mappers;

import fr.fms.apitrainings.entities.Category;
import fr.fms.apitrainings.entities.CategoryDTO;

public class CategoryMapper {

    public static CategoryDTO mapToDto(Category category) {

        CategoryDTO categoryDTO = CategoryDTO.builder()
                .id(category.getId())
                .name(category.getName())
                .build();

        return categoryDTO;
    }

    public static Category mapToEntity(CategoryDTO categoryDTO) {

        Category category = Category.builder()
                .id(categoryDTO.getId())
                .name(categoryDTO.getName())
                .build();

        return category;
    }
}
