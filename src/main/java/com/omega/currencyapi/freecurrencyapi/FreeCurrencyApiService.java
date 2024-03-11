package com.omega.currencyapi.freecurrencyapi;

import java.time.LocalDate;

import org.springframework.format.annotation.DateTimeFormat;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Query;

public interface FreeCurrencyApiService {

        @GET("/v1/currencies")
        Call<FreeCurrencyApiCurrencyResponse> getCurrency(@Query("apikey") String apiKey);

        @GET("/v1/latest")
        Call<FreeCurrencyApiRateResponse> getLatestCurrencyRate(@Query("apikey") String apiKey,
                        @Query("base_currency") String baseCurrency);

        @GET("/v1/historical")
        Call<FreeCurrencyApiHistoricalRateResponse> getHistoricalCurrencyRate(@Query("apikey") String apiKey,
                        @Query("base_currency") String baseCurrency,
                        @Query("date") @DateTimeFormat(pattern = "yyyy-MM-dd") LocalDate date);

}
