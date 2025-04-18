package com.linhpete.java6.presentation.request_handler.dto.request;

import jakarta.validation.constraints.PositiveOrZero;
import lombok.*;
import lombok.experimental.FieldDefaults;

import java.util.Set;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
@FieldDefaults(level = AccessLevel.PRIVATE)
public class ProductRequest {
    String name;
    String image;
    @PositiveOrZero
    int quantity;
    String description;
    Float price;
    Set<String> categories;
}
