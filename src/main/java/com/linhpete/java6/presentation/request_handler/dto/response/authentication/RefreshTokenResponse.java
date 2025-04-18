package com.linhpete.java6.presentation.request_handler.dto.response.authentication;

import lombok.*;
import lombok.experimental.FieldDefaults;

@AllArgsConstructor
@NoArgsConstructor
@Data
@Builder
@FieldDefaults(level = AccessLevel.PRIVATE)
public class RefreshTokenResponse {
    String token;
    boolean success;
}
