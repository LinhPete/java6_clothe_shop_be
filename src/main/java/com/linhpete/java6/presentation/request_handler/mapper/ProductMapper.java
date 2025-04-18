package com.linhpete.java6.presentation.request_handler.mapper;

import com.linhpete.java6.presentation.request_handler.dto.request.ProductRequest;
import com.linhpete.java6.presentation.request_handler.dto.response.ProductResponse;
import com.linhpete.java6.persistence.entity.Product;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.MappingTarget;
import org.springframework.stereotype.Component;

@Mapper(componentModel = "spring")
@Component
public interface ProductMapper extends AbstractMapper<Product, ProductRequest, ProductResponse> {
    @Override
    @Mapping(target = "categories", ignore = true)
    Product mapFromRequest(ProductRequest productRequest);

    @Override
    @Mapping(target = "categories", ignore = true)
    void updateEntity(@MappingTarget Product product, ProductRequest request);

    @Override
    @Mapping(target = "categories", ignore = true)
    ProductResponse mapToResponse(Product product);
}
