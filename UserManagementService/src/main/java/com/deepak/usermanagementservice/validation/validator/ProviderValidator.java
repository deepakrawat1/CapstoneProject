package com.deepak.usermanagementservice.validation.validator;

import com.deepak.usermanagementservice.enums.AuthProvider;
import com.deepak.usermanagementservice.enums.Gender;
import com.deepak.usermanagementservice.validation.annotation.ValidGender;
import com.deepak.usermanagementservice.validation.annotation.ValidProvider;
import jakarta.validation.ConstraintValidator;
import jakarta.validation.ConstraintValidatorContext;

public class ProviderValidator implements ConstraintValidator<ValidProvider, String> {

    @Override
    public boolean isValid(String value, ConstraintValidatorContext context) {

        if (value == null) return false;

        try {
            AuthProvider.valueOf(value.toUpperCase());
            return true;
        } catch (Exception e) {
            return false;
        }
    }
}
