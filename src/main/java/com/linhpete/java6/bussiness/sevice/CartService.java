package com.linhpete.java6.bussiness.sevice;

import com.linhpete.java6.presentation.request_handler.dto.response.CartResponse;

public interface CartService {
    CartResponse getCartByCustomer(String customerId);
    CartResponse createCart(String customerId);
}
