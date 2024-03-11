package com.omega.currencyapi.controller;

import java.time.LocalDate;
import java.util.List;
import java.util.Map;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

import com.omega.currencyapi.dto.CurrencyResponse;
import com.omega.currencyapi.service.CurrencyApiService;
import com.omega.currencyapi.utils.CurrencyConstraint;

import jakarta.validation.Valid;
import jakarta.validation.constraints.Past;
import jakarta.websocket.server.PathParam;

@RestController
public class CurrencyApiController {

    @Autowired
    CurrencyApiService service;

    @GetMapping("/currencies")
    public List<CurrencyResponse> getCurrency() {
        return service.getCurrencies();
    }

    @GetMapping("/latest")
    public Map<String, Double> getCurrencyRates(
            @PathParam("baseCurrency") @Validated @CurrencyConstraint(message = "The '${validatedValue}' not a valid currency code!") Optional<String> baseCurrency) {
        return service.getLatestCurrencyRates(baseCurrency.orElse("USD"));
    }

    @GetMapping("/historical/{date}")
    public Map<String, Double> getCurrencyRates(
            @PathParam("baseCurrency") @Validated @CurrencyConstraint(message="The '${validatedValue}' not a valid currency code!") Optional<String> baseCurrency,
            @PathVariable("date") @Valid @Past LocalDate date) {
        return service.getHistoricalCurrencyRates(baseCurrency.orElse("USD"), date);
    }

}
