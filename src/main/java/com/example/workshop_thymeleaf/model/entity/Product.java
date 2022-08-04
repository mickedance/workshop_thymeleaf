package com.example.workshop_thymeleaf.model.entity;

import lombok.*;
import org.springframework.stereotype.Component;

import javax.persistence.*;
import javax.validation.constraints.NotEmpty;
import java.time.LocalDate;

@Setter
@Getter
@ToString
@AllArgsConstructor
@EqualsAndHashCode
@NoArgsConstructor

@Entity
public class Product {
    @Id
    @Column(nullable = false, unique = true, updatable = true)
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;
    @Column(nullable = false, unique = true)
    private String name;
    @Column(nullable = false)
    private Double price;
    @ManyToOne( cascade = {CascadeType.REFRESH, CascadeType.DETACH}, fetch = FetchType.EAGER)
    private Category category;
    @Column(updatable = false)
    private LocalDate createDate = LocalDate.now();

    public Product(String name, double price, Category category) {
        this.name = name;
        this.price = price;
        this.category = category;
    }
}
