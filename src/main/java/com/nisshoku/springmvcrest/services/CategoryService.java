package com.nisshoku.springmvcrest.services;

import com.nisshoku.springmvcrest.api.v1.model.CategoryDTO;

import java.util.List;

public interface CategoryService {

    List<CategoryDTO> getAllCategories();

    CategoryDTO getCategoryByName(String name);
}
