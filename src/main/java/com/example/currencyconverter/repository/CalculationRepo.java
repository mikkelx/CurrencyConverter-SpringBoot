package com.example.currencyconverter.repository;

import com.example.currencyconverter.module.CalculatedUnit;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface CalculationRepo extends JpaRepository<CalculatedUnit, Long> {

    void deleteCalculatedUnitByid(Long id);

    Optional<CalculatedUnit> findCalculatedUnitByid(Long id);
}
