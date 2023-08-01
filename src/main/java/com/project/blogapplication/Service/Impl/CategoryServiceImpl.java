package com.project.blogapplication.Service.Impl;

import com.project.blogapplication.Exceptions.ResourceNotFoundException;
import com.project.blogapplication.Model.Category;
import com.project.blogapplication.Payload.CategoryDto;
import com.project.blogapplication.Repository.CategoryRepository;
import com.project.blogapplication.Service.CategoryService;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

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

    @Override
    public CategoryDto getCategoryById(Long id) {
        Category category=categoryRepository.findById(id)
                .orElseThrow(()->new ResourceNotFoundException("Category","id",id));
        return modelMapper.map(category,CategoryDto.class);
    }

    @Override
    public List<CategoryDto> getAllCategories() {
        List<Category> categories=categoryRepository.findAll();
        return categories.stream().map(category -> modelMapper.map(category,CategoryDto.class)).collect(Collectors.toList());
    }

    @Override
    public CategoryDto updateCategory(CategoryDto categoryDto, Long categoryId) {
        Category foundCategory=categoryRepository.findById(categoryId)
                .orElseThrow(()-> new ResourceNotFoundException("Category","id",categoryId));
        foundCategory.setName(categoryDto.getName());
        foundCategory.setDescription(categoryDto.getDescription());
        foundCategory.setId(categoryId);

       Category category= categoryRepository.save(foundCategory);
       return modelMapper.map(category,CategoryDto.class);
    }

    @Override
    public void deleteCategory(Long id) {
        Category category=categoryRepository.findById(id).orElseThrow(()->new ResourceNotFoundException("Category","id",id));
        categoryRepository.delete(category);

    }
}
