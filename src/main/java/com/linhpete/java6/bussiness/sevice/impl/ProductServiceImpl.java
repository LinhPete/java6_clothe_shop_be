package com.linhpete.java6.bussiness.sevice.impl;

import com.linhpete.java6.presentation.request_handler.dto.request.ProductRequest;
import com.linhpete.java6.presentation.request_handler.dto.response.ProductResponse;
import com.linhpete.java6.bussiness.exception.AppException;
import com.linhpete.java6.presentation.request_handler.mapper.ProductMapper;
import com.linhpete.java6.bussiness.sevice.ProductService;
import com.linhpete.java6.persistence.entity.Category;
import com.linhpete.java6.persistence.entity.Product;
import com.linhpete.java6.persistence.repository.CategoryRepository;
import com.linhpete.java6.persistence.repository.ProductRepository;
import com.linhpete.java6.presentation.api_response.code_enum.ErrorCode;
import lombok.AccessLevel;
import lombok.RequiredArgsConstructor;
import lombok.experimental.FieldDefaults;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Set;

@Service
@RequiredArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE, makeFinal = true)
public class ProductServiceImpl implements ProductService {

    CategoryRepository categoryRepository;
    ProductRepository productRepository;
    ProductMapper productMapper;

    @Override
    public ProductResponse createProduct(ProductRequest productRequest) {
        Product product = productMapper.mapFromRequest(productRequest);
        Set<Category> categories = categoryRepository.findAllByNameIsIn(productRequest.getCategories());
        product.setCategories(categories);
        return productMapper.mapToResponse(productRepository.save(product));
    }

    @Override
    public List<ProductResponse> getAllProducts() {
        List<Product> products = productRepository.findAll();
        if (products.isEmpty()) {
            throw new AppException(ErrorCode.NO_ENTITIES_FOUND);
        }
        return products.stream().map(productMapper::mapToResponse).toList();
    }

    @Override
    public List<ProductResponse> getProductsByCategory(String category) {
        List<Product> products = productRepository.findAllByCategory(category);
        if (products.isEmpty()) {
            throw new AppException(ErrorCode.NO_ENTITIES_FOUND);
        }
        return products.stream().map(productMapper::mapToResponse).toList();
    }

    @Override
    public ProductResponse getProduct(long id) {
        return productMapper.mapToResponse(
                productRepository.findById(id)
                        .orElseThrow(() -> new AppException(ErrorCode.ENTITY_NOT_FOUND))
        );
    }

    @Override
    public ProductResponse updateProduct(long id, ProductRequest productRequest) {
        Product product = productRepository.findById(id).orElseThrow(() -> new AppException(ErrorCode.ENTITY_NOT_FOUND));
        productMapper.updateEntity(product, productRequest);
        Set<Category> categories = categoryRepository.findAllByNameIsIn(productRequest.getCategories());
        product.setCategories(categories);
        return productMapper.mapToResponse(productRepository.save(product));
    }

    @Override
    public void deleteProduct(long id) {
        if (!productRepository.existsById(id)) {
            throw new AppException(ErrorCode.ENTITY_NOT_FOUND);
        }
        productRepository.deleteById(id);
    }
}
