package com.linhpete.java6.presentation.request_handler.dto.request.product;

import java.util.Set;

public class ProductCreationRequest {
    String name;
    String image;
    int quantity;
    String description;
    Float price;
    Set<String> categories;
}
