package com.example.workshop_thymeleaf.converter;

import com.example.workshop_thymeleaf.model.dto.CategoryView;
import com.example.workshop_thymeleaf.model.entity.Category;

import java.util.List;
import java.util.stream.Collectors;

public interface converter<E, V> {
    V entityToView(E entity);
    List<V> entitiesToViews(List<E> entities);

    E viewToEntity(V view);
}
