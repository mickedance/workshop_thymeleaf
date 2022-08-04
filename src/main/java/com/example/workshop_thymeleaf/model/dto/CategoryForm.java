package com.example.workshop_thymeleaf.model.dto;

import lombok.*;

import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.Size;

@Setter
@Getter
@AllArgsConstructor
@NoArgsConstructor
@ToString
public class CategoryForm {

    @NotEmpty
    @Size(min = 2, max = 20)
    private String name;
}
