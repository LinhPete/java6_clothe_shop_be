package com.linhpete.java6.presentation.request_handler.dto.request.user;

import com.linhpete.java6.presentation.request_handler.dto.validator.constraint.Password;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.*;
import lombok.experimental.FieldDefaults;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
@FieldDefaults(level = AccessLevel.PRIVATE)
public class UserPwdUpdateRequest {
    @NotNull(message = "BLANK_FIELDS")
    @NotBlank(message = "BLANK_FIELDS")
    String token;
    @NotNull(message = "BLANK_FIELDS")
    @NotBlank(message = "BLANK_FIELDS")
    String oldPassword;
    @NotNull(message = "BLANK_FIELDS")
    @NotBlank(message = "BLANK_FIELDS")
    @Password
    String newPassword;
}
