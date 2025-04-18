package com.linhpete.java6.presentation.request_handler.dto.response;

import lombok.*;
import lombok.experimental.FieldDefaults;
import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
@FieldDefaults(level = AccessLevel.PRIVATE)
public class CartResponse {
    String id;
    String userId;
    List<CartItemResponse> cartItemList;
    Double total;
}
