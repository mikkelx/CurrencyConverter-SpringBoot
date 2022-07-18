package com.example.currencyconverter.controller;

import com.example.currencyconverter.exception.RateNofFoundExecption;
import com.example.currencyconverter.module.CalculatedUnit;
import com.example.currencyconverter.module.CurrencyRates;
import com.example.currencyconverter.service.CurrencyRatesService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/currencyRates")
public class CurrencyRatesController {
    private final CurrencyRatesService currencyRatesService;

    @Autowired
    public CurrencyRatesController(CurrencyRatesService currencyRatesService) {
        this.currencyRatesService = currencyRatesService;
    }

    @GetMapping("/all")
    public ResponseEntity<List<CurrencyRates>> getAllRates() {
        List<CurrencyRates> currencyRatesList = currencyRatesService.findAllRates();
        return new ResponseEntity<>(currencyRatesList, HttpStatus.OK);
    }

    @GetMapping("/find/{id}")
    public ResponseEntity<CurrencyRates> getCurrencyRateById(@PathVariable("id") Long id) {
        CurrencyRates currencyRates = null;
        try {
            currencyRates = currencyRatesService.getCurrencyRatesById(id);
        } catch (RateNofFoundExecption e) {
            throw new RuntimeException(e);
        }
        return new ResponseEntity<>(currencyRates, HttpStatus.OK);
    }

    @GetMapping("/find/{currencyShortcut}")
    public ResponseEntity<CurrencyRates> getCurrencyRateBycurrencyShortcut(@PathVariable("currencyShortcut") String currencyShortcut) {
        CurrencyRates currencyRates = null;
        try {
            currencyRates = currencyRatesService.getCurrencyBycurrencyShortcut(currencyShortcut);
        } catch (RateNofFoundExecption e) {
            throw new RuntimeException(e);
        }
        return new ResponseEntity<>(currencyRates, HttpStatus.OK);
    }

    @PostMapping("/add")
    public ResponseEntity<CurrencyRates> addCurrencyRate(@RequestBody CurrencyRates currencyRates) {
        CurrencyRates newCurrencRate = currencyRatesService.addCurrencyRates(currencyRates);
        return new ResponseEntity<>(newCurrencRate, HttpStatus.CREATED);
    }

    @PutMapping("/update")
    public ResponseEntity<CurrencyRates> updateCurrencyRate(@RequestBody CurrencyRates currencyRates) {
        CurrencyRates updateRate = currencyRatesService.updateRate(currencyRates);
        return new ResponseEntity<>(currencyRates, HttpStatus.OK);
    }

}
