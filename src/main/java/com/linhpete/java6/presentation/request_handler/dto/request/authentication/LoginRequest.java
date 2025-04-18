package com.linhpete.java6.presentation.request_handler.dto.request.authentication;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.*;
import lombok.experimental.FieldDefaults;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
@FieldDefaults(level = AccessLevel.PRIVATE)
public class LoginRequest {
    @NotNull(message = "BLANK_FIELDS")
    @NotBlank(message = "BLANK_FIELDS")
    String email;
    @NotNull(message = "BLANK_FIELDS")
    @NotBlank(message = "BLANK_FIELDS")
    String password;
}
