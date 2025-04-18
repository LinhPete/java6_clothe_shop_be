package com.linhpete.java6.bussiness.sevice.impl;

import com.linhpete.java6.presentation.request_handler.dto.response.CartResponse;
import com.linhpete.java6.bussiness.exception.AppException;
import com.linhpete.java6.presentation.request_handler.mapper.CartItemMapper;
import com.linhpete.java6.presentation.request_handler.mapper.CartMapper;
import com.linhpete.java6.bussiness.sevice.CartItemService;
import com.linhpete.java6.bussiness.sevice.CartService;
import com.linhpete.java6.persistence.entity.Cart;
import com.linhpete.java6.persistence.repository.CartRepository;
import com.linhpete.java6.presentation.api_response.code_enum.ErrorCode;
import lombok.AccessLevel;
import lombok.RequiredArgsConstructor;
import lombok.experimental.FieldDefaults;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE, makeFinal = true)
public class CartServiceImpl implements CartService {
    CartRepository cartRepository;
    CartMapper cartMapper;
    CartItemService cartItemService;
    CartItemMapper cartItemMapper;
    @Override
    public CartResponse getCartByCustomer(String customerId) {
        Cart cart = cartRepository.findByUser(customerId).orElseThrow(() -> new AppException(ErrorCode.ENTITY_NOT_FOUND));
        CartResponse cartResponse = cartMapper.mapToResponse(cart);
        cartResponse.setCartItemList(
                cart.getItems().stream().map(cartItemMapper::mapToResponse).toList()
        );
        return cartResponse;
    }

    @Override
    public CartResponse createCart(String customerId) {

        return null;
    }
}
