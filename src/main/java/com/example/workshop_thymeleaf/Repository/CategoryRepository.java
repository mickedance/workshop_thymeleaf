package com.example.workshop_thymeleaf.Repository;

import com.example.workshop_thymeleaf.model.entity.Category;
import org.springframework.data.repository.CrudRepository;

import java.util.List;

public interface CategoryRepository extends CrudRepository<Category, Integer> {
    @Override
    List<Category> findAll();
}
