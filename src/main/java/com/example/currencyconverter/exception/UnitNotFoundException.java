package com.example.currencyconverter.exception;

public class UnitNotFoundException extends Exception{
    UnitNotFoundException(){};

    public UnitNotFoundException(String exMessage) {
        super(exMessage);
    }
}
