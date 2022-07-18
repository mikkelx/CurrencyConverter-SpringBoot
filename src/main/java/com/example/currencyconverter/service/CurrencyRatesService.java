package com.example.currencyconverter.service;

import com.example.currencyconverter.exception.DuplicateException;
import com.example.currencyconverter.exception.RateNofFoundExecption;
import com.example.currencyconverter.module.CurrencyRates;
import com.example.currencyconverter.repository.CurrencyRatesRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.List;
import java.util.Optional;

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

    @Transactional
    public void updateRate(String currencyShortcut, float rate) throws RateNofFoundExecption {
        CurrencyRates currencyRates = currencyRatesRepo.findCurrencyBycurrencyShortcut(currencyShortcut).orElseThrow(() ->
                                                        new RateNofFoundExecption("Rate not found"));

        if(rate>0)
            currencyRates.setRate(rate);

    }

    public CurrencyRates getCurrencyRatesById(Long Id) throws RateNofFoundExecption{
        return currencyRatesRepo.findCurrencyRatesById(Id).orElseThrow(() ->new RateNofFoundExecption("Currency rate nof dound!"));
    }

    public CurrencyRates getCurrencyBycurrencyShortcut(String currencyShortcut) throws RateNofFoundExecption{
        return currencyRatesRepo.findCurrencyBycurrencyShortcut(currencyShortcut).orElseThrow(() ->new RateNofFoundExecption("Currency rate nof dound!"));
    }

    public boolean isDuplicate(CurrencyRates currencyRates) {
        Optional<CurrencyRates> newCurrencyRates = currencyRatesRepo.findCurrencyBycurrencyShortcut(currencyRates.getCurrencyShortcut());
        if(newCurrencyRates.isPresent())
            return true;
        else return false;
    }


}
