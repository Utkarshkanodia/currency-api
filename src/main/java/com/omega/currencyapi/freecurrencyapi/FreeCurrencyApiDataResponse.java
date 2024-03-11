package com.omega.currencyapi.freecurrencyapi;

import com.fasterxml.jackson.annotation.JsonProperty;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class FreeCurrencyApiDataResponse {

    @JsonProperty("symbol")
    private String symbol;

    @JsonProperty("name")
    private String name;

    @JsonProperty("symbol_native")
    private String symbol_native;

    @JsonProperty("decimal_digits")
    private int decimal_digits;

    @JsonProperty("rounding")
    private int rounding;

    @JsonProperty("code")
    private String code;

    @JsonProperty("name_plural")
    private String name_plural;

    @JsonProperty("type")
    private String type;
}
