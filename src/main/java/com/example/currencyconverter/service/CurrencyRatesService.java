package com.example.currencyconverter.service;

import com.example.currencyconverter.exception.RateNofFoundExecption;
import com.example.currencyconverter.module.CurrencyRates;
import com.example.currencyconverter.repository.CurrencyRatesRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class CurrencyRatesService {
    private final CurrencyRatesRepo currencyRatesRepo;

    @Autowired
    public CurrencyRatesService(CurrencyRatesRepo currencyRatesRepo) {
        this.currencyRatesRepo = currencyRatesRepo;
    }

    public CurrencyRates addCurrencyRates(CurrencyRates currencyRates) {
        return currencyRatesRepo.save(currencyRates);
    }

    public List<CurrencyRates> findAllRates() {
        return currencyRatesRepo.findAll();
    }

    public CurrencyRates updateRate(CurrencyRates currencyRates) {
        return currencyRatesRepo.save(currencyRates);
    }

    public CurrencyRates getCurrencyRatesById(Long Id) throws RateNofFoundExecption{
        return currencyRatesRepo.findCurrencyRatesById(Id).orElseThrow(() ->new RateNofFoundExecption("Currency rate nof dound!"));
    }

    public CurrencyRates getCurrencyBycurrencyShortcut(String currencyShortcut) throws RateNofFoundExecption{
        return currencyRatesRepo.findCurrencyBycurrencyShortcut(currencyShortcut).orElseThrow(() ->new RateNofFoundExecption("Currency rate nof dound!"));
    }

}
