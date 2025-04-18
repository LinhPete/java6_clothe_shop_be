package com.linhpete.java6.presentation.request_handler.dto.response;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.*;
import lombok.experimental.FieldDefaults;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
@FieldDefaults(level = AccessLevel.PRIVATE)
public class OrderDetailResponse {
    String id;
    @NotNull
    @NotBlank
    @NotEmpty
    Long productId;
    @NotNull
    @NotBlank
    @NotEmpty
    String orderId;
    @Size(min = 1)
    Integer quantity;
    Double subtotal;
}
