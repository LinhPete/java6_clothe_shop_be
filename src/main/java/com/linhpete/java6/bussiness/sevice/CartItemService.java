package com.linhpete.java6.bussiness.sevice;

import com.linhpete.java6.presentation.request_handler.dto.request.cart_item.CartItemAddRequest;
import com.linhpete.java6.presentation.request_handler.dto.response.CartItemResponse;

public interface CartItemService {
    CartItemResponse addCartItem(CartItemAddRequest cartItemAddRequest);
    CartItemResponse updateCartItem(CartItemAddRequest cartItemAddRequest);
    void deleteCartItem(String itemId);
}
