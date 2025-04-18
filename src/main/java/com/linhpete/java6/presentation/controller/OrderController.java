package com.linhpete.java6.presentation.controller;

import com.linhpete.java6.presentation.request_handler.dto.request.OrderRequest;
import com.linhpete.java6.presentation.request_handler.dto.response.OrderResponse;
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
@RequestMapping("/orders")
@RequiredArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE, makeFinal = true)
public class OrderController {
    @GetMapping
    ResponseEntity<APIResponse<List<OrderResponse>>> getAllOrders(){
        return ResponseEntityFactory.getResponseEntity(
                ErrorCode.UNIMPLEMENTED_ERROR,
                null
        );
    }
    @GetMapping("/{id}")
    ResponseEntity<APIResponse<OrderResponse>> getOrderById(@PathVariable("id") String id){
        return ResponseEntityFactory.getResponseEntity(
                ErrorCode.UNIMPLEMENTED_ERROR,
                null
        );
    }
    @GetMapping("/{customer-id}")
    ResponseEntity<APIResponse<List<OrderResponse>>> getOrderByCustomer(@PathVariable("customer-id") String id){
        return ResponseEntityFactory.getResponseEntity(
                ErrorCode.UNIMPLEMENTED_ERROR,
                null
        );
    }
    @PostMapping
    ResponseEntity<APIResponse<OrderResponse>> createOrder(@RequestBody OrderRequest request){
        return ResponseEntityFactory.getResponseEntity(
                ErrorCode.UNIMPLEMENTED_ERROR,
                null
        );
    }
    @PatchMapping("/{id}")
    ResponseEntity<APIResponse<OrderResponse>> updateOrder(@PathVariable("id") String id, @RequestBody OrderRequest request){
        return ResponseEntityFactory.getResponseEntity(
                ErrorCode.UNIMPLEMENTED_ERROR,
                null
        );
    }
    @DeleteMapping("/{id}")
    ResponseEntity<APIResponse<Void>> closeOrder(@PathVariable("id") String id){
        return ResponseEntityFactory.getResponseEntity(
                ErrorCode.UNIMPLEMENTED_ERROR,
                null
        );
    }
}
