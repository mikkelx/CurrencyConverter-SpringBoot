package com.example.currencyconverter.service;

import com.example.currencyconverter.exception.RateNofFoundExecption;
import com.example.currencyconverter.exception.UnitNotFoundException;
import com.example.currencyconverter.module.CalculatedUnit;
import com.example.currencyconverter.module.CurrencyRates;
import com.example.currencyconverter.repository.CalculationRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.nio.file.attribute.UserPrincipalNotFoundException;
import java.util.List;

@Service
public class CalculationService {

    private final CalculationRepo calculationRepo;

    @Autowired
    public CalculationService(CalculationRepo calculationRepo) {
        this.calculationRepo = calculationRepo;
    }

    public CalculatedUnit addCalculationUnit(CalculatedUnit calculatedUnit) {
        return calculationRepo.save(calculatedUnit);
    }

    public List<CalculatedUnit> findAllUnits() {
        return calculationRepo.findAll();
    }

//    public void updateUnit(Long id, float inputAmount, String inputCurrency,
//                                     float outputAmount, String outputCurrency) throws RateNofFoundExecption {
//        CalculatedUnit calculatedUnit = calculationRepo.findCalculatedUnitByid(id).orElseThrow(() ->
//                new RateNofFoundExecption("Rate not found"));
//        if (inputAmount >= 0 && outputAmount >= 0) {
//            calculatedUnit.setInputAmount(inputAmount);
//            calculatedUnit.setOutputAmount(outputAmount);
//            calculatedUnit.setInputCurrency(inputCurrency);
//            calculatedUnit.setOutputCurrency(outputCurrency);
//
//        }
//    }

    public CalculatedUnit findCalculatedUnitByid(Long id) throws UnitNotFoundException{
        return calculationRepo.findCalculatedUnitByid(id)
                .orElseThrow(() -> new UnitNotFoundException("Not found!"));
    }

    public void deleteUnit(Long id) {
        calculationRepo.deleteCalculatedUnitByid(id);
    }
}
