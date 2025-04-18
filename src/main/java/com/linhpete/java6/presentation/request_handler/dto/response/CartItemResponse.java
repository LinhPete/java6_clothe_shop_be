package com.linhpete.java6.presentation.request_handler.dto.response;

import lombok.*;
import lombok.experimental.FieldDefaults;

import java.time.LocalDate;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
@FieldDefaults(level = AccessLevel.PRIVATE)
public class CartItemResponse {
    String id;
    Long productId;
    String cartId;
    LocalDate addedDate;
    Integer quantity;
    Double subtotal;
}
