package com.example.workshop_thymeleaf.model.dto;

import com.example.workshop_thymeleaf.model.entity.Category;
import lombok.*;
import org.springframework.format.annotation.DateTimeFormat;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.FetchType;
import javax.persistence.ManyToOne;
import java.time.LocalDate;

@Setter
@Getter
@NoArgsConstructor
@AllArgsConstructor
@ToString
@EqualsAndHashCode
public class ProductView {
    private int id;
     private String name;
    private double price;
    private CategoryView categoryView;
    @DateTimeFormat(iso = DateTimeFormat.ISO.DATE)
    private LocalDate createDate;
}
