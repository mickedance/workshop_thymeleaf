package com.example.workshop_thymeleaf.service;

import com.example.workshop_thymeleaf.model.dto.CategoryForm;
import com.example.workshop_thymeleaf.model.dto.CategoryView;
import com.example.workshop_thymeleaf.model.dto.ProductForm;
import com.example.workshop_thymeleaf.model.dto.ProductView;

import java.util.List;

public interface ProductService {
    List<ProductView> findAll();

    ProductView create(ProductForm productForm);

    boolean deleteById(int id);
    ProductView findById(int id);

    ProductView Update(ProductForm form);
}
