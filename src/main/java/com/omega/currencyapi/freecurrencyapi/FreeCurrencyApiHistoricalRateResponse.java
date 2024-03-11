package com.omega.currencyapi.freecurrencyapi;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.Map;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class FreeCurrencyApiHistoricalRateResponse {
    public Map<String, Map<String, Double>> data;

    public Map<String, Double> getCurrencyData(LocalDate date) {
        return data.get(date.format(DateTimeFormatter.ofPattern("yyyy-MM-dd")));
    }
}
