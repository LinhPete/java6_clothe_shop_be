package com.linhpete.java6.presentation.request_handler.mapper;

import com.linhpete.java6.presentation.request_handler.dto.request.CategoryRequest;
import com.linhpete.java6.presentation.request_handler.dto.response.CategoryResponse;
import com.linhpete.java6.persistence.entity.Category;
import org.mapstruct.Mapper;
import org.springframework.stereotype.Component;

@Mapper(componentModel = "spring")
@Component
public interface CategoryMapper {
    Category mapFromRequest(CategoryRequest request);
    CategoryResponse mapToResponse(Category category);
}
