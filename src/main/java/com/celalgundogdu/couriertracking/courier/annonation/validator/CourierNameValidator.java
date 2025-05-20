package com.celalgundogdu.couriertracking.courier.annonation.validator;

import com.celalgundogdu.couriertracking.courier.annonation.ValidCourierName;
import jakarta.validation.ConstraintValidator;
import jakarta.validation.ConstraintValidatorContext;
import org.apache.commons.lang3.StringUtils;
import org.springframework.stereotype.Component;

@Component
public class CourierNameValidator implements ConstraintValidator<ValidCourierName, String> {

    @Override
    public boolean isValid(String value, ConstraintValidatorContext constraintValidatorContext) {
        value = value.replaceAll("[^a-zA-Z0-9]", "");
        return !StringUtils.isNumeric(value) && !StringUtils.isAllBlank(value);
    }
}
