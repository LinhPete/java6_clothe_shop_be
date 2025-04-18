package com.linhpete.java6.presentation.request_handler.dto.request.cart_item;

import jakarta.validation.constraints.Size;
import lombok.*;
import lombok.experimental.FieldDefaults;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
@FieldDefaults(level = AccessLevel.PRIVATE)
public class CartItemUpdateRequest {
    @Size(min = 1)
    Integer quantity;
}
