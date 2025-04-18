package com.linhpete.java6.presentation.request_handler.dto.request.user;

import jakarta.validation.constraints.Email;
import lombok.*;
import lombok.experimental.FieldDefaults;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
@FieldDefaults(level = AccessLevel.PRIVATE)
public class UserUpdateRequest {
    @Email(message = "INVALID_EMAIL")
    String email;
    String fullName;
    String phone;
    Boolean gender;
    String role;
}
