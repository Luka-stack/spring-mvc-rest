package com.nisshoku.springmvcrest.api.v1.mapper;

import com.nisshoku.springmvcrest.api.v1.model.CategoryDTO;
import com.nisshoku.springmvcrest.domain.Category;
import org.junit.Test;

import static org.junit.Assert.*;

public class CategoryMapperTest {

    CategoryMapper categoryMapper = CategoryMapper.INSTANCE;

    @Test
    public void categoryToCategoryDTO() throws Exception {

        // given
        Category category = new Category();
        category.setName("Cat");
        category.setId(1L);

        // when
        CategoryDTO categoryDTO = categoryMapper.categoryToCategoryDTO(category);

        // then
        assertEquals(Long.valueOf(1L), categoryDTO.getId());
        assertEquals("Cat", categoryDTO.getName());
    }

}