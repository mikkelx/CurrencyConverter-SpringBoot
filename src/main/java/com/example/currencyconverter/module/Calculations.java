package com.example.currencyconverter.module;

import com.example.currencyconverter.exception.RateNofFoundExecption;
import com.example.currencyconverter.service.CurrencyRatesService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component("calculations")
public class Calculations {
    private final CurrencyRatesService currencyRatesService;

    @Autowired
    public Calculations(CurrencyRatesService currencyRatesService) {
        this.currencyRatesService = currencyRatesService;
    }

    public float calculateOutputAmount(String outputCurrency, float money) {
        try {
            CurrencyRates currencyRates = currencyRatesService.getCurrencyBycurrencyShortcut(outputCurrency);
            return (money * currencyRates.getRate());
        } catch (RateNofFoundExecption e) {
            throw new RuntimeException(e);
        }
    }
}
