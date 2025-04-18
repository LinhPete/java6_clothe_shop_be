package com.linhpete.java6.bussiness.sevice;

import com.linhpete.java6.presentation.request_handler.dto.request.CategoryRequest;
import com.linhpete.java6.presentation.request_handler.dto.response.CategoryResponse;

import java.util.List;

public interface CategoryService {
    List<CategoryResponse> getAllCategories();
    CategoryResponse getCategoryById(long id);
    CategoryResponse createCategory(CategoryRequest request);
    void deleteCategory(long id);
}
