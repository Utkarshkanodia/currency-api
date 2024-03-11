package com.omega.currencyapi.freecurrencyapi;

import java.util.Map;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class FreeCurrencyApiCurrencyResponse {

    private Map<String,FreeCurrencyApiDataResponse> data;

}
