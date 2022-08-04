package com.example.workshop_thymeleaf.Repository;

import com.example.workshop_thymeleaf.model.entity.Product;
import org.springframework.data.repository.CrudRepository;

import java.util.List;

public interface ProductRepository extends CrudRepository<Product, Integer> {
    List<Product> findAll();
}
