package com.linhpete.java6.presentation.request_handler.dto.validator;

import com.linhpete.java6.presentation.request_handler.dto.validator.constraint.Password;
import jakarta.validation.ConstraintValidator;
import jakarta.validation.ConstraintValidatorContext;
import org.springframework.beans.factory.annotation.Value;

public class PasswordValidator implements ConstraintValidator<Password, String> {

    @Value("${validation.constraint.password.reg-ex}")
    private String regEx;

    @Override
    public void initialize(Password constraintAnnotation) {
        ConstraintValidator.super.initialize(constraintAnnotation);
    }

    @Override
    public boolean isValid(String s, ConstraintValidatorContext constraintValidatorContext) {
        return s.isBlank() || s.matches(regEx);
    }
}
