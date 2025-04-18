package com.linhpete.java6.presentation.controller;

import com.linhpete.java6.presentation.request_handler.dto.response.CartResponse;
import com.linhpete.java6.bussiness.sevice.CartService;
import com.linhpete.java6.presentation.api_response.APIResponse;
import com.linhpete.java6.presentation.api_response.ResponseEntityFactory;
import com.linhpete.java6.presentation.api_response.code_enum.SuccessCode;
import lombok.AccessLevel;
import lombok.RequiredArgsConstructor;
import lombok.experimental.FieldDefaults;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@Slf4j
@RestController
@RequestMapping("/carts")
@RequiredArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE, makeFinal = true)
public class CartController {
    CartService cartService;
    @GetMapping("/{userId}")
    ResponseEntity<APIResponse<CartResponse>> getCartByCustomer(@PathVariable String userId) {
        return ResponseEntityFactory.getResponseEntity(
                SuccessCode.ENTITY_FOUND,
                cartService.getCartByCustomer(userId)
        );
    }
}
