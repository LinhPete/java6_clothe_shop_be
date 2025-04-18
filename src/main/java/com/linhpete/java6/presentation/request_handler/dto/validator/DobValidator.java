package com.linhpete.java6.presentation.request_handler.dto.validator;

import com.linhpete.java6.presentation.request_handler.dto.validator.constraint.Dob;
import jakarta.validation.ConstraintValidator;
import jakarta.validation.ConstraintValidatorContext;

import java.time.LocalDate;
import java.time.temporal.ChronoUnit;
import java.util.Objects;

public class DobValidator implements ConstraintValidator<Dob, LocalDate> {

    private int min;

    @Override
    public void initialize(Dob constraintAnnotation) {
        ConstraintValidator.super.initialize(constraintAnnotation);
        min = constraintAnnotation.min();
    }

    @Override
    public boolean isValid(LocalDate localDate, ConstraintValidatorContext constraintValidatorContext) {
        if(Objects.isNull(localDate)) return true;
        long years = ChronoUnit.YEARS.between(localDate, LocalDate.now());

        return years >= min;
    }
}
