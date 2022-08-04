package com.example.workshop_thymeleaf.converter;

import com.example.workshop_thymeleaf.model.dto.CategoryView;
import com.example.workshop_thymeleaf.model.entity.Category;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.stream.Collectors;

@Component
public class CategoryConverter {
    public CategoryView entityToView(Category entity){
        if(entity == null) return null;
        return new CategoryView(entity.getId(), entity.getName(), entity.getDate());
    }
    public List<CategoryView> entitiesToViews(List<Category> entities){
        if(entities == null) return  null;
        return  entities.stream().map(this::entityToView).collect(Collectors.toList());
    }

    public Category viewToEntity(CategoryView categoryView){
        if(categoryView==null) return null;
        return new Category(categoryView.getName());
    }
}
