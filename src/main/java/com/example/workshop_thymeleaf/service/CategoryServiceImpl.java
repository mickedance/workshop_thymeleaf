package com.example.workshop_thymeleaf.service;

import com.example.workshop_thymeleaf.Repository.CategoryRepository;
import com.example.workshop_thymeleaf.converter.CategoryConverter;
import com.example.workshop_thymeleaf.model.dto.CategoryForm;
import com.example.workshop_thymeleaf.model.dto.CategoryView;
import com.example.workshop_thymeleaf.model.entity.Category;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class CategoryServiceImpl implements CategoryService{

    private CategoryRepository categoryRepository;
    private CategoryConverter categoryConverter;

    @Autowired
    public CategoryServiceImpl(CategoryRepository categoryRepository, CategoryConverter categoryConverter){
        this.categoryRepository = categoryRepository;
        this.categoryConverter = categoryConverter;
    }

    @Override
    public List<CategoryView> findAll() {
        System.out.println("find all service");
        return categoryConverter.entitiesToViews(categoryRepository.findAll());
    }

    @Override
    public CategoryView create(CategoryForm form) {
        if(form==null) throw new IllegalArgumentException("category form was null");
        Category category = new Category(form.getName());
        Category savedCategory = categoryRepository.save(category);
        return categoryConverter.entityToView(savedCategory);

    }

    public boolean deleteById(int id) {
        if(id<0) throw new IllegalArgumentException("id must be 0 or larger");
        CategoryView view = findById(id);
        if(view!=null) {
            categoryRepository.deleteById(id);
            return true;
        }
        return false;
    }

    public CategoryView findById(int id){
        Optional<Category> optional = categoryRepository.findById(id);
        if(optional.isPresent()){
            return  categoryConverter.entityToView(optional.get());
        }
        return null;
    }
}
