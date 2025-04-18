package com.linhpete.java6.bussiness.sevice;


import com.linhpete.java6.presentation.request_handler.dto.request.ProductRequest;
import com.linhpete.java6.presentation.request_handler.dto.response.ProductResponse;

import java.util.List;

public interface ProductService {
    ProductResponse createProduct(ProductRequest productRequest);
    List<ProductResponse> getAllProducts();
    List<ProductResponse> getProductsByCategory(String category);
    ProductResponse getProduct(long id);
    ProductResponse updateProduct(long id, ProductRequest productRequest);
    void deleteProduct(long id);
}
