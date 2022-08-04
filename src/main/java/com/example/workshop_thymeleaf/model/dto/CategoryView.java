package com.example.workshop_thymeleaf.model.dto;

import lombok.*;

import java.time.LocalDate;

@Setter
@Getter
@NoArgsConstructor
@AllArgsConstructor
@ToString
@EqualsAndHashCode
public class CategoryView {
    private int id;
    private String name;
    private LocalDate date;
}
