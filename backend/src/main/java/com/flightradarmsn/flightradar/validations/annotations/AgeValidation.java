package com.flightradarmsn.flightradar.validations.annotations;

import com.flightradarmsn.flightradar.validations.validators.AgeValidator;
import jakarta.validation.Constraint;
import jakarta.validation.Payload;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

/*
* Annotation personalizada responsavel por verificar se o usuario possui
* mais de 18 anos
* */

@Target({ ElementType.FIELD })
@Retention(RetentionPolicy.RUNTIME)
@Constraint(validatedBy = AgeValidator.class)
public @interface AgeValidation {

    String message() default "User must be at least 18 years old";

    Class<?>[] groups() default {};
    Class<? extends Payload>[] payload() default {};

}
