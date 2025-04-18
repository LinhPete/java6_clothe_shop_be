package com.linhpete.java6.presentation.request_handler.mapper;

import com.linhpete.java6.presentation.request_handler.dto.request.CartRequest;
import com.linhpete.java6.presentation.request_handler.dto.response.CartResponse;
import com.linhpete.java6.persistence.entity.Cart;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.MappingTarget;
import org.springframework.stereotype.Component;

@Mapper(componentModel = "spring")
@Component
public interface CartMapper {
    Cart mapFromRequest(CartRequest request);
    void updateEntity(@MappingTarget Cart cart, CartRequest request);
    @Mapping(target = "cartItemList", source = "items", ignore = true)
    CartResponse mapToResponse(Cart cart);
}
