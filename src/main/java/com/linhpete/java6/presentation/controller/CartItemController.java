package com.linhpete.java6.presentation.controller;

import com.linhpete.java6.presentation.request_handler.dto.request.cart_item.CartItemAddRequest;
import com.linhpete.java6.presentation.request_handler.dto.response.CartItemResponse;
import com.linhpete.java6.presentation.api_response.APIResponse;
import com.linhpete.java6.presentation.api_response.ResponseEntityFactory;
import com.linhpete.java6.presentation.api_response.code_enum.ErrorCode;
import lombok.AccessLevel;
import lombok.RequiredArgsConstructor;
import lombok.experimental.FieldDefaults;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Slf4j
@RestController
@RequestMapping("/cart-items")
@RequiredArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE, makeFinal = true)
public class CartItemController {
    @GetMapping("/{cart-id}")
    ResponseEntity<APIResponse<List<CartItemResponse>>> getAllCartItems(@PathVariable("cart-id") String cartId) {
        return ResponseEntityFactory.getResponseEntity(
                ErrorCode.UNIMPLEMENTED_ERROR,
                null
        );
    }
    @GetMapping("/{cart-id}/{item-id}")
    ResponseEntity<APIResponse<CartItemResponse>> getCartItemById(@PathVariable("cart-id") String cartId,@PathVariable("item-id") String itemId){
        return ResponseEntityFactory.getResponseEntity(
                ErrorCode.UNIMPLEMENTED_ERROR,
                null
        );
    }
    @PostMapping("/{cart-id}")
    ResponseEntity<APIResponse<CartItemResponse>> addCartItem(@PathVariable("cart-id") String cartId, @RequestBody CartItemAddRequest cartItemAddRequest){
        return ResponseEntityFactory.getResponseEntity(
                ErrorCode.UNIMPLEMENTED_ERROR,
                null
        );
    }
    @PatchMapping("/{cart-id}/{item-id}")
    ResponseEntity<APIResponse<CartItemResponse>> updateCartItem(@PathVariable("cart-id") String cartId,@PathVariable("item-id") String itemId, @RequestBody CartItemAddRequest cartItemUpdateRequest){
        return ResponseEntityFactory.getResponseEntity(
                ErrorCode.UNIMPLEMENTED_ERROR,
                null
        );
    }
    @DeleteMapping("/{cart-id}/{item-id}")
    ResponseEntity<APIResponse<Void>> removeCartItem(@PathVariable("cart-id") String cartId,@PathVariable("item-id") String itemId){
        return ResponseEntityFactory.getResponseEntity(
                ErrorCode.UNIMPLEMENTED_ERROR,
                null
        );
    }
}
