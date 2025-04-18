package com.linhpete.java6.presentation.request_handler.dto.request.user;

import com.linhpete.java6.presentation.request_handler.dto.validator.constraint.Password;
import jakarta.validation.constraints.Email;
import lombok.*;
import lombok.experimental.FieldDefaults;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
@FieldDefaults(level = AccessLevel.PRIVATE)
public class UserRegisterRequest {
    @Email(message = "INVALID_EMAIL")
    String email;
    @Password(message = "INVALID_PASSWORD")
    String password;
}
