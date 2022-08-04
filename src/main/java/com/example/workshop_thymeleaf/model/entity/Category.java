package com.example.workshop_thymeleaf.model.entity;

import lombok.*;

import javax.persistence.*;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

@Setter
@Getter
@ToString
@AllArgsConstructor
@EqualsAndHashCode
@Entity
public class Category {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(nullable = false, updatable = false, unique = true)
    private Integer id;
    @Column(unique = true)
    private String name;
    @OneToMany(mappedBy = "category", cascade = {CascadeType.REFRESH, CascadeType.DETACH})
    private List<Product> productList;
    private LocalDate date;

    public Category() {
        this.date = LocalDate.now();
    }
    public Category(String name) {
        this();
        this.name = name;
    }

    public void addProduct(Product product){
        if(productList==null) productList= new ArrayList<>();
        productList.add(product);
        product.setCategory(this);
    }
    public void removeProduct(Product product){
        if(productList==null) productList= new ArrayList<>();
        productList.remove(product);
        product.setCategory(null);
    }
}
