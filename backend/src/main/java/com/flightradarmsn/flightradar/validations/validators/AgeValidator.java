package com.flightradarmsn.flightradar.validations.validators;

import com.flightradarmsn.flightradar.validations.annotations.AgeValidation;
import jakarta.validation.ConstraintValidator;
import jakarta.validation.ConstraintValidatorContext;

import java.time.LocalDate;

/*
* Classe responsavel por validar se o usuario possui mais de 18 anos
* */

public class AgeValidator implements ConstraintValidator<AgeValidation, LocalDate> {
    @Override
    public boolean isValid(LocalDate birthDate, ConstraintValidatorContext constraintValidatorContext) {
        return birthDate.isBefore(LocalDate.now().minusYears(18))
                || birthDate.isEqual(LocalDate.now().minusYears(18));
    }
}
