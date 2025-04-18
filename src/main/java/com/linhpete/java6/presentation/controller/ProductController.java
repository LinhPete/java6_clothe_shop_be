package com.linhpete.java6.presentation.controller;

import com.linhpete.java6.presentation.request_handler.dto.request.ProductRequest;
import com.linhpete.java6.presentation.request_handler.dto.response.ProductResponse;
import com.linhpete.java6.bussiness.sevice.ProductService;
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
@RequestMapping("/products")
@RequiredArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE, makeFinal = true)
public class ProductController {
    ProductService productService;
    @GetMapping
    ResponseEntity<APIResponse<List<ProductResponse>>> getAllProducts(){
        return ResponseEntityFactory.getResponseEntity(
                SuccessCode.ENTITIES_FOUND,
                productService.getAllProducts()
        );
    }
    @GetMapping("/{category}")
    ResponseEntity<APIResponse<List<ProductResponse>>> getProductsByCategory(@PathVariable("category") String category){
        return ResponseEntityFactory.getResponseEntity(
                SuccessCode.ENTITIES_FOUND,
                productService.getProductsByCategory(category)
        );
    }
    @GetMapping("/{id}")
    ResponseEntity<APIResponse<ProductResponse>> getProductById(@PathVariable("id") String id){
        return ResponseEntityFactory.getResponseEntity(
                SuccessCode.ENTITY_FOUND,
                productService.getProduct(Long.parseLong(id))
        );
    }
    @PostMapping
    ResponseEntity<APIResponse<ProductResponse>> createProduct(@RequestBody ProductRequest request){
        return ResponseEntityFactory.getResponseEntity(
                SuccessCode.ENTITY_CREATED,
                productService.createProduct(request)
        );
    }
    @PutMapping("/{id}")
    ResponseEntity<APIResponse<ProductResponse>> updateProduct(@PathVariable("id") String id, @RequestBody ProductRequest request){
        return ResponseEntityFactory.getResponseEntity(
                SuccessCode.ENTITY_UPDATED,
                productService.updateProduct(Long.parseLong(id), request)
        );
    }
    @DeleteMapping("/{id}")
    ResponseEntity<APIResponse<Void>> deleteProduct(@PathVariable("id") String id){
        productService.deleteProduct(Long.parseLong(id));
        return ResponseEntityFactory.getResponseEntity(
                SuccessCode.ENTITY_DELETED,
                null
        );
    }
}
