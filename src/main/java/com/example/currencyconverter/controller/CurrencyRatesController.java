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
import java.util.Optional;

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
        boolean duplicate = currencyRatesService.isDuplicate(currencyRates);
        if(duplicate){
//            currencyRatesService.updateRate(currencyRates);
            return new ResponseEntity<>(HttpStatus.ALREADY_REPORTED);
        }
        else {
            CurrencyRates newCurrencyRate = currencyRatesService.addCurrencyRates(currencyRates);
            return new ResponseEntity<>(newCurrencyRate, HttpStatus.CREATED);
        }

    }

    @PutMapping("/update")
    public void updateCurrencyRate(@RequestBody CurrencyRates currencyRates) {
        try {
            currencyRatesService.updateRate(currencyRates.getCurrencyShortcut(), currencyRates.getRate());
        } catch (RateNofFoundExecption e) {
            throw new RuntimeException(e);
        }
    }

}
