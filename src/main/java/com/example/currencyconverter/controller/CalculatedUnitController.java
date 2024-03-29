package com.example.currencyconverter.controller;

import com.example.currencyconverter.exception.UnitNotFoundException;
import com.example.currencyconverter.module.CalculatedUnit;
import com.example.currencyconverter.module.Calculations;
import com.example.currencyconverter.service.CalculationService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/unit")
public class CalculatedUnitController {
    private final CalculationService calculationService;
    private final Calculations calculations;

    @Autowired
    public CalculatedUnitController(CalculationService calculationService, Calculations calculations) {
        this.calculationService = calculationService;
        this.calculations = calculations;
    }

    @GetMapping("/all")
    public ResponseEntity<List<CalculatedUnit>> getAllUnits() {
        List<CalculatedUnit> calculatedUnits = calculationService.findAllUnits();
        return new ResponseEntity<>(calculatedUnits, HttpStatus.OK);
    }

    @GetMapping("/find/{id}")
    public ResponseEntity<CalculatedUnit> getUnitById(@PathVariable("id") Long id) {
        CalculatedUnit calculatedUnit = null;
        try {
            calculatedUnit = calculationService.findCalculatedUnitByid(id);
        } catch (UnitNotFoundException e) {
            throw new RuntimeException(e);
        }
        return new ResponseEntity<>(calculatedUnit, HttpStatus.OK);
    }

    @PostMapping("/add")
    public ResponseEntity<CalculatedUnit> addUnit(@RequestBody CalculatedUnit calculatedUnit) {
        calculatedUnit.setOutputAmount(this.calculations.calculateOutputAmount(calculatedUnit.getOutputCurrency(),
                calculatedUnit.getInputAmount()));
        CalculatedUnit newCalculatedUnit1 = calculationService.addCalculationUnit(calculatedUnit);
        return new ResponseEntity<>(newCalculatedUnit1, HttpStatus.CREATED);
    }

//    @PutMapping("/update")
//    public void updateUnit(@RequestBody CalculatedUnit calculatedUnit) {
//        try {
//            calculationService.updateUnit(calculatedUnit.getId(), calculatedUnit.getInputAmount(),
//                                          calculatedUnit.getInputCurrency(), calculatedUnit.getOutputAmount(),
//                                          calculatedUnit.getOutputCurrency());
//        } catch (RateNofFoundExecption e) {
//            throw new RuntimeException(e);
//        }
//    }

    @DeleteMapping("/delete/{id}")
    public ResponseEntity<?> deleteUnit(@PathVariable("id") Long id) {
        calculationService.deleteUnit(id);
        return new ResponseEntity<>(HttpStatus.OK);
    }
}
