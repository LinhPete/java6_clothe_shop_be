package com.linhpete.java6.bussiness.sevice.impl;

import com.linhpete.java6.presentation.request_handler.dto.request.CategoryRequest;
import com.linhpete.java6.presentation.request_handler.dto.response.CategoryResponse;
import com.linhpete.java6.bussiness.exception.AppException;
import com.linhpete.java6.presentation.request_handler.mapper.CategoryMapper;
import com.linhpete.java6.bussiness.sevice.CategoryService;
import com.linhpete.java6.persistence.entity.Category;
import com.linhpete.java6.persistence.repository.CategoryRepository;
import com.linhpete.java6.presentation.api_response.code_enum.ErrorCode;
import lombok.AccessLevel;
import lombok.RequiredArgsConstructor;
import lombok.experimental.FieldDefaults;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE, makeFinal = true)
public class CategoryServiceImpl implements CategoryService {

    CategoryRepository categoryRepository;
    CategoryMapper categoryMapper;
    @Override
    public List<CategoryResponse> getAllCategories() {
        List<Category> categories = categoryRepository.findAll();
        if(categories.isEmpty()) {
            throw new AppException(ErrorCode.NO_ENTITIES_FOUND);
        }
        return categories.stream().map(categoryMapper::mapToResponse).toList();
    }

    @Override
    public CategoryResponse getCategoryById(long id) {
        return categoryMapper.mapToResponse(
                categoryRepository.findById(id)
                        .orElseThrow(() -> new AppException(ErrorCode.ENTITY_NOT_FOUND))
        );
    }

    @Override
    public CategoryResponse createCategory(CategoryRequest request) {
        if(categoryRepository.existsByName(request.getName())) {
            throw new AppException(ErrorCode.ENTITY_ALREADY_EXISTS);
        }
        Category category = categoryMapper.mapFromRequest(request);
        return categoryMapper.mapToResponse(categoryRepository.save(category));
    }

    @Override
    public void deleteCategory(long id) {
        if(!categoryRepository.existsById(id)) {
            throw new AppException(ErrorCode.ENTITY_NOT_FOUND);
        }
        categoryRepository.deleteById(id);
    }
}
