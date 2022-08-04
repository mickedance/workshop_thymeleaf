package com.example.workshop_thymeleaf.service;

import com.example.workshop_thymeleaf.Repository.CategoryRepository;
import com.example.workshop_thymeleaf.Repository.ProductRepository;
import com.example.workshop_thymeleaf.converter.ProductConverter;
import com.example.workshop_thymeleaf.model.dto.CategoryForm;
import com.example.workshop_thymeleaf.model.dto.ProductForm;
import com.example.workshop_thymeleaf.model.dto.ProductView;
import com.example.workshop_thymeleaf.model.entity.Category;
import com.example.workshop_thymeleaf.model.entity.Product;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ProductServiceImpl implements ProductService {

    private ProductRepository productRepository;
    private ProductConverter productConverter;
    private CategoryRepository categoryRepository;

    @Autowired
    public ProductServiceImpl(ProductRepository productRepository, ProductConverter productConverter, CategoryRepository categoryRepository) {
        this.productConverter = productConverter;
        this.productRepository = productRepository;
        this.categoryRepository = categoryRepository;
    }

    @Override
    public List<ProductView> findAll() {
        List<Product> productList = productRepository.findAll();

        return productConverter.entitiesToViews(productList);
    }

    @Override
    public ProductView create(ProductForm productForm) {
        Integer id = new Integer(productForm.getCategoryId());
        Category category = categoryRepository.findById(id).orElse(null);
        Double price = new Double(productForm.getPrice());
        if (category == null) throw new IllegalArgumentException("no valid category provided");
        Product product = new Product(productForm.getName(), price, category);
        Product savedProduct = productRepository.save(product);

        return productConverter.entityToView(savedProduct);
    }

    @Override
    public boolean deleteById(int id) {
        if (id < 0) throw new IllegalArgumentException("id must be 0 or more");
        if (findById(id) == null) {
            return false;
        }
        productRepository.deleteById(id);
        return true;
    }

    @Override
    public ProductView findById(int id) {
        Product product = productRepository.findById(id).orElse(null);
        return productConverter.entityToView(product);
    }


    public ProductView Update(ProductForm form) {
        Product product = productRepository.findById(form.getId()).orElse(null);
        if (product == null)
            return null;
        product.getCategory().removeProduct(product);
        product.setCreateDate(form.getCreateDate());
        product.setId(form.getId());
        Category category = categoryRepository.findById(Integer.valueOf(form.getCategoryId())).orElse(null);
        if(category ==null) throw new IllegalArgumentException("category was not found/null");
        product.setCategory(category);
        product.setName(form.getName());
        product.setPrice(form.getPrice());
        Product savedProduct = productRepository.save(product);
        return null;
    }
}
