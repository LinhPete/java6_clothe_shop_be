package com.linhpete.java6.presentation.request_handler.dto.validator.constraint;

import com.linhpete.java6.presentation.request_handler.dto.validator.DobValidator;
import jakarta.validation.Constraint;
import jakarta.validation.Payload;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

@Target({ElementType.FIELD})
@Retention(RetentionPolicy.RUNTIME)
@Constraint(validatedBy = {DobValidator.class})
public @interface Dob {
    String message() default "INVALID_DOB";

    int min();

    Class<?>[] groups() default {};

    Class<? extends Payload>[] payload() default {};
}
