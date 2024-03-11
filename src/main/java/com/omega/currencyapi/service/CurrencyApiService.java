package com.omega.currencyapi.service;

import java.io.IOException;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;

import org.springframework.stereotype.Service;

import com.omega.currencyapi.dto.CurrencyResponse;
import com.omega.currencyapi.exception.ApiResponseException;
import com.omega.currencyapi.exception.FreeCurrencyApiExcuteException;
import com.omega.currencyapi.freecurrencyapi.FreeCurrencyApiConfiguration;
import com.omega.currencyapi.freecurrencyapi.FreeCurrencyApiCurrencyResponse;
import com.omega.currencyapi.freecurrencyapi.FreeCurrencyApiDataResponse;
import com.omega.currencyapi.freecurrencyapi.FreeCurrencyApiHistoricalRateResponse;
import com.omega.currencyapi.freecurrencyapi.FreeCurrencyApiRateResponse;
import com.omega.currencyapi.freecurrencyapi.FreeCurrencyApiService;

import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

@Service
public class CurrencyApiService {
    private FreeCurrencyApiService service;

    private String apiKey;

    public CurrencyApiService() {
        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl(FreeCurrencyApiConfiguration.API_BASE_URL)
                .addConverterFactory(GsonConverterFactory.create())
                .build();
        service = retrofit.create(FreeCurrencyApiService.class);
        apiKey = FreeCurrencyApiConfiguration.API_KEY;
    }

    public List<CurrencyResponse> getCurrencies() {
        List<CurrencyResponse> methodResponse = new ArrayList<>();
        Response<FreeCurrencyApiCurrencyResponse> response;
        try {
            response = service.getCurrency(apiKey).execute();
            if (!response.isSuccessful()) {
                throw new ApiResponseException(response.message(), response.code());
            }
        } catch (IOException e) {
            throw new FreeCurrencyApiExcuteException("get currency failed ");
        }
        FreeCurrencyApiCurrencyResponse reponseBody = response.body();
        Map<String, FreeCurrencyApiDataResponse> currencyMap = reponseBody.getData();
        for (String code : currencyMap.keySet()) {
            FreeCurrencyApiDataResponse currencyData = currencyMap.get(code);
            methodResponse.add(CurrencyResponse.builder()
                    .symbol(currencyData.getSymbol())
                    .name(currencyData.getName())
                    .symbolNative(currencyData.getSymbol_native())
                    .decimalDigits(currencyData.getDecimal_digits())
                    .rounding(currencyData.getRounding())
                    .code(currencyData.getCode())
                    .namePlural(currencyData.getName_plural())
                    .type(currencyData.getType())
                    .build());
        }
        return methodResponse;
    }

    public Set<String> getCurrenciesCodes() {
        List<CurrencyResponse> currencyList = getCurrencies();
        Set<String> currencyCodes = new HashSet<>();
        currencyList.forEach(currencyData -> {
            currencyCodes.add(currencyData.getCode());
        });
        return currencyCodes;
    }

    public Map<String, Double> getLatestCurrencyRates(String baseCurrency) {
        Response<FreeCurrencyApiRateResponse> response;
        try {
            response = service.getLatestCurrencyRate(apiKey, baseCurrency).execute();
            if (!response.isSuccessful()) {
                throw new ApiResponseException(response.message(), response.code());
            }
        } catch (IOException e) {
            throw new FreeCurrencyApiExcuteException("get latest currency rate failed");
        }

        FreeCurrencyApiRateResponse reponseBody = response.body();
        return reponseBody.getData();
    }

    public Map<String, Double> getHistoricalCurrencyRates(String baseCurrency, LocalDate date) {
        Response<FreeCurrencyApiHistoricalRateResponse> response;
        try {
            response = service.getHistoricalCurrencyRate(apiKey, baseCurrency, date).execute();
            if (!response.isSuccessful()) {
                throw new ApiResponseException(response.message(), response.code());
            }
        } catch (IOException e) {
            throw new FreeCurrencyApiExcuteException("get historical currency failed");
        }
        return response.body().getCurrencyData(date);
    }
}
