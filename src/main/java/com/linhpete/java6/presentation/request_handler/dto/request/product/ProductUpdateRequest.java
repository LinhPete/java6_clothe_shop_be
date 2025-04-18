package com.linhpete.java6.presentation.request_handler.dto.request.product;

import jakarta.validation.constraints.Min;

public class ProductUpdateRequest {
    int quantity;
    @Min(1)
    Float price;
}
