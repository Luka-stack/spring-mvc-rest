package com.nisshoku.springmvcrest.services;

import com.nisshoku.springmvcrest.api.v1.mapper.CategoryMapper;
import com.nisshoku.springmvcrest.api.v1.model.CategoryDTO;
import com.nisshoku.springmvcrest.domain.Category;
import com.nisshoku.springmvcrest.repositories.CategoryRepository;
import org.junit.Before;
import org.junit.Test;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import java.util.Arrays;
import java.util.List;

import static org.junit.Assert.*;
import static org.mockito.ArgumentMatchers.anyString;
import static org.mockito.Mockito.when;

public class CategoryServiceImplTest {

    private static final Long ID = 2L;
    private static final String NAME = "Kirito";
    private CategoryService categoryService;

    @Mock
    CategoryRepository categoryRepository;

    @Before
    public void setUp() throws Exception {
        MockitoAnnotations.initMocks(this);

        categoryService = new CategoryServiceImpl(CategoryMapper.INSTANCE, categoryRepository);
    }

    @Test
    public void getAllCategoriesTest() throws Exception {

        // given
        List<Category> categories = Arrays.asList(new Category(), new Category(), new Category());

        when(categoryRepository.findAll()).thenReturn(categories);

        // when
        List<CategoryDTO> categoryDTOS = categoryService.getAllCategories();

        // then
        assertEquals(3, categoryDTOS.size());
    }

    @Test
    public void getCategoryByNameTest() throws Exception {

        // given
        Category category = new Category();
        category.setName(NAME);
        category.setId(ID);

        when(categoryRepository.findByName(anyString())).thenReturn(category);

        // when
        CategoryDTO categoryDTO = categoryService.getCategoryByName(NAME);

        // then
        assertEquals(ID, categoryDTO.getId());
        assertEquals(NAME, categoryDTO.getName());;
    }
}