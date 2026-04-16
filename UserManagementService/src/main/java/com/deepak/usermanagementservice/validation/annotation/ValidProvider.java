package com.deepak.usermanagementservice.validation.annotation;

import com.deepak.usermanagementservice.validation.validator.GenderValidator;
import com.deepak.usermanagementservice.validation.validator.ProviderValidator;
import jakarta.validation.Constraint;
import jakarta.validation.Payload;

import java.lang.annotation.*;

@Documented
@Constraint(validatedBy = ProviderValidator.class)
@Target({ ElementType.FIELD })
@Retention(RetentionPolicy.RUNTIME)
public @interface ValidProvider {
    String message() default "Invalid provider value";

    Class<?>[] groups() default {};

    Class<? extends Payload>[] payload() default {};
}
