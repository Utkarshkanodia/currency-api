package com.omega.currencyapi.dto;

import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

@Builder
@Getter
@Setter
public class CurrencyResponse {
    private String symbol;
    private String name;
    private String symbolNative;
    private int decimalDigits;
    private int rounding;
    private String code;
    private String namePlural;
    private String type;
}
