package com.cv.cv_c_r_u_d.customannotation;

import java.text.SimpleDateFormat;
import java.util.Date;

import jakarta.validation.ConstraintValidator;
import jakarta.validation.ConstraintValidatorContext;

public class DateValidator implements ConstraintValidator<ValidDate, Date> {

    private static final String DATE_FORMAT = "yyyy-MM-dd";

    @Override
    public boolean isValid(Date value, ConstraintValidatorContext context) {
        if (value == null) {
            return true; // null values are valid, use @NotNull for null checks
        }
        SimpleDateFormat sdf = new SimpleDateFormat(DATE_FORMAT);
        sdf.setLenient(false);
        try {
            String dateString = sdf.format(value);
            Date date = sdf.parse(dateString);
            return date != null;
        } catch (Exception e) {
            return false;
        }
    }
}
