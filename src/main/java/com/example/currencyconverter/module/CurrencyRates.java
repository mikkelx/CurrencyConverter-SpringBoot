package com.example.currencyconverter.module;

import com.sun.istack.NotNull;
import lombok.*;

import javax.persistence.*;

@Table
@Entity
@AllArgsConstructor
@NoArgsConstructor
@Setter
@Getter
public class CurrencyRates {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @NonNull
    private String currencyShortcut;
    @NonNull
    private float rate;

}
