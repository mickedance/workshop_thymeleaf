package com.example.workshop_thymeleaf.service;

import com.example.workshop_thymeleaf.model.dto.CategoryForm;
import com.example.workshop_thymeleaf.model.dto.CategoryView;
import com.example.workshop_thymeleaf.model.entity.Category;

import java.util.List;

public interface CategoryService {
    List<CategoryView> findAll();

    CategoryView create(CategoryForm categoryForm);

    boolean deleteById(int id);
    CategoryView findById(int id);
}
