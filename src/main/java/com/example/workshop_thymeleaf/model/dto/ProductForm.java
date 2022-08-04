package com.example.workshop_thymeleaf.model.dto;

import lombok.*;

import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.Size;
import java.time.LocalDate;

@Setter
@Getter
@NoArgsConstructor
@AllArgsConstructor
@ToString
@EqualsAndHashCode
public class ProductForm {
    @Size(min =2, max = 100)
    @NotEmpty
    private String name;
    @NotEmpty
    private String categoryId;
    private double price;
    private int id;
    private LocalDate createDate;
}
