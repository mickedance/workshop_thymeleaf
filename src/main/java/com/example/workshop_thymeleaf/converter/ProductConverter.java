package com.example.workshop_thymeleaf.converter;

import com.example.workshop_thymeleaf.model.dto.CategoryView;
import com.example.workshop_thymeleaf.model.dto.ProductForm;
import com.example.workshop_thymeleaf.model.dto.ProductView;
import com.example.workshop_thymeleaf.model.entity.Category;
import com.example.workshop_thymeleaf.model.entity.Product;
import com.example.workshop_thymeleaf.service.CategoryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.stream.Collectors;
@Component
public class ProductConverter implements converter<Product, ProductView> {
    private CategoryConverter categoryConverter;
    private CategoryService categoryService;

    @Autowired
    public ProductConverter(CategoryConverter categoryConverter, CategoryService categoryService){
        this.categoryConverter = categoryConverter;
        this.categoryService = categoryService;
    }
    @Override
    public ProductView entityToView(Product entity) {
        if(entity==null ) return  null;
        return new ProductView(entity.getId(), entity.getName(), entity.getPrice(), categoryConverter.entityToView(entity.getCategory()), entity.getCreateDate() );

    }

    @Override
    public List<ProductView> entitiesToViews(List<Product> entities) {
        if(entities==null) return null;
        return entities.stream().map(this::entityToView).collect(Collectors.toList());
    }

    @Override
    public Product viewToEntity(ProductView view) {
        Category category = categoryConverter.viewToEntity(view.getCategoryView());
        return new Product(view.getId(), view.getName(), view.getPrice(), category, view.getCreateDate() );
    }
    public ProductView formToView(ProductForm form){
        CategoryView categoryView = categoryService.findById( Integer.valueOf(form.getCategoryId()) );
        ProductView view = new ProductView(form.getId(), form.getName(), form.getPrice(), categoryView, form.getCreateDate());
        return view;
    }
}
