package com.cv.cv_c_r_u_d.customannotation;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

import jakarta.validation.Constraint;
import jakarta.validation.Payload;

@Constraint(validatedBy = TaskSearchValidator.class)
@Target({ ElementType.PARAMETER, ElementType.FIELD })
@Retention(RetentionPolicy.RUNTIME)
public @interface ValidTaskSearch {
    String message() default "Invalid search parameters";

    Class<?>[] groups() default {};

    Class<? extends Payload>[] payload() default {};
}
