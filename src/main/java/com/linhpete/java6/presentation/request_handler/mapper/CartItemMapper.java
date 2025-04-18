package com.linhpete.java6.presentation.request_handler.mapper;

import com.linhpete.java6.presentation.request_handler.dto.request.cart_item.CartItemAddRequest;
import com.linhpete.java6.presentation.request_handler.dto.request.cart_item.CartItemUpdateRequest;
import com.linhpete.java6.presentation.request_handler.dto.response.CartItemResponse;
import com.linhpete.java6.persistence.entity.CartItem;
import org.mapstruct.Mapper;
import org.mapstruct.MappingTarget;
import org.springframework.stereotype.Component;

@Mapper(componentModel = "spring")
@Component
public interface CartItemMapper extends AbstractMapper<CartItem, CartItemAddRequest, CartItemResponse>{
    CartItem mapFromRequest(CartItemAddRequest request);
    void updateEntity(@MappingTarget CartItem item, CartItemUpdateRequest request);
    CartItemResponse mapToResponse(CartItem item);
}
