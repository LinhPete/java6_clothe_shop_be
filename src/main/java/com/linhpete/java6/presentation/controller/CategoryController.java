package com.linhpete.java6.presentation.controller;

import com.linhpete.java6.presentation.request_handler.dto.request.CategoryRequest;
import com.linhpete.java6.presentation.request_handler.dto.response.CategoryResponse;
import com.linhpete.java6.bussiness.sevice.CategoryService;
import com.linhpete.java6.presentation.api_response.APIResponse;
import com.linhpete.java6.presentation.api_response.ResponseEntityFactory;
import com.linhpete.java6.presentation.api_response.code_enum.SuccessCode;
import lombok.AccessLevel;
import lombok.RequiredArgsConstructor;
import lombok.experimental.FieldDefaults;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Slf4j
@RestController
@RequestMapping("/categories")
@RequiredArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE, makeFinal = true)
public class CategoryController {
    CategoryService categoryService;
    @GetMapping
    ResponseEntity<APIResponse<List<CategoryResponse>>> getAllCategories(){
        return ResponseEntityFactory.getResponseEntity(
                SuccessCode.ENTITIES_FOUND,
                categoryService.getAllCategories()
        );
    }
    @GetMapping("/{id}")
    ResponseEntity<APIResponse<CategoryResponse>> getCategoryById(@PathVariable("id") String id){
        return ResponseEntityFactory.getResponseEntity(
                SuccessCode.ENTITY_FOUND,
                categoryService.getCategoryById(Long.parseLong(id))
        );
    }
    @PostMapping
    ResponseEntity<APIResponse<CategoryResponse>> createCategory(@RequestBody CategoryRequest request){
        return ResponseEntityFactory.getResponseEntity(
                SuccessCode.ENTITY_CREATED,
                categoryService.createCategory(request)
        );
    }
    @PutMapping("/{id}")
    ResponseEntity<APIResponse<CategoryResponse>> updateCategory(@PathVariable("id") String id, @RequestBody CategoryRequest request){
        return ResponseEntityFactory.getResponseEntity(
                SuccessCode.ENTITY_UPDATED,
                categoryService.updateCategory(Long.parseLong(id), request)
        );
    }
    @DeleteMapping("/{id}")
    ResponseEntity<APIResponse<Void>> deleteCategory(@PathVariable("id") String id){
        categoryService.deleteCategory(Long.parseLong(id));
        return ResponseEntityFactory.getResponseEntity(
                SuccessCode.ENTITY_DELETED,
                null
        );
    }
}
