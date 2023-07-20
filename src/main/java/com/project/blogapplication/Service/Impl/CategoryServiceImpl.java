package com.project.blogapplication.Service.Impl;

import com.project.blogapplication.Model.Category;
import com.project.blogapplication.Payload.CategoryDto;
import com.project.blogapplication.Repository.CategoryRepository;
import com.project.blogapplication.Service.CategoryService;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;

@Service
public class CategoryServiceImpl implements CategoryService {

    private final CategoryRepository categoryRepository;
    private final ModelMapper modelMapper;

    public CategoryServiceImpl(CategoryRepository categoryRepository, ModelMapper modelMapper) {
        this.categoryRepository = categoryRepository;
        this.modelMapper = modelMapper;
    }

    @Override
    public CategoryDto addCategory(CategoryDto categoryDto) {
        Category category=modelMapper.map(categoryDto,Category.class);
       Category savedCategory= categoryRepository.save(category);
       return modelMapper.map(savedCategory,CategoryDto.class);
    }
}
