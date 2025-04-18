package com.linhpete.java6.presentation.request_handler.dto.request;

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
public class OrderDetailRequest {
    @NotNull
    @NotBlank
    @NotEmpty
    Long productId;
    @NotNull
    @NotBlank
    @NotEmpty
    String cartId;
    @Size(min = 1)
    Integer quantity;
}
