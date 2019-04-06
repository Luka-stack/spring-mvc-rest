package com.nisshoku.springmvcrest.api.v1.mapper;

import com.nisshoku.springmvcrest.api.v1.model.CategoryDTO;
import com.nisshoku.springmvcrest.domain.Category;
import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

@Mapper
public interface CategoryMapper {

    CategoryMapper INSTANCE = Mappers.getMapper(CategoryMapper.class);

    CategoryDTO categoryToCategoryDTO(Category category);
}
