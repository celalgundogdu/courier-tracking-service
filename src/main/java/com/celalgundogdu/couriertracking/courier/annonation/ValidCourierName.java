package com.celalgundogdu.couriertracking.courier.annonation;

import com.celalgundogdu.couriertracking.courier.annonation.validator.CourierNameValidator;
import jakarta.validation.Constraint;
import jakarta.validation.Payload;

import java.lang.annotation.Documented;
import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

@Documented
@Target({ElementType.FIELD, ElementType.PARAMETER})
@Retention(RetentionPolicy.RUNTIME)
@Constraint(validatedBy = CourierNameValidator.class)
public @interface ValidCourierName {

    String message() default "Courier name is invalid";
    Class<?>[] groups() default {};
    Class<? extends Payload>[] payload() default {};
}
