package com.omega.currencyapi.utils;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;

import com.omega.currencyapi.service.CurrencyApiService;

import jakarta.validation.ConstraintValidator;
import jakarta.validation.ConstraintValidatorContext;

public class CurrencyValidator implements ConstraintValidator<CurrencyConstraint, Optional<String>> {

    @Autowired
    CurrencyApiService service;

    @Override
    public void initialize(CurrencyConstraint constraintAnnotation) {

    }

    @Override
    public boolean isValid(Optional<String> value, ConstraintValidatorContext context) {
        if (value.isPresent())
            return service.getCurrenciesCodes().contains(value.get());
        return true;
    }

    

}
