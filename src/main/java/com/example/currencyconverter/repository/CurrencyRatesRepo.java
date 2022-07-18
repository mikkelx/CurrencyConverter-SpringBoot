package com.example.currencyconverter.repository;

import com.example.currencyconverter.module.CurrencyRates;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface CurrencyRatesRepo extends JpaRepository<CurrencyRates, Long> {


    Optional<CurrencyRates> findCurrencyRatesById(Long id);

    Optional<CurrencyRates> findCurrencyBycurrencyShortcut(String currencyShortcut);
}
