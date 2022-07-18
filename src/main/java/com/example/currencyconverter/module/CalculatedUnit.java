package com.example.currencyconverter.module;

import com.sun.istack.NotNull;
import lombok.*;
import org.springframework.beans.factory.annotation.Autowired;

import javax.persistence.*;

@Table
@Entity
@AllArgsConstructor
@NoArgsConstructor
@Setter
@Getter
public class CalculatedUnit {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @NonNull
    private float inputAmount;
    @NonNull
    private String inputCurrency;
    //@Transient

    private float outputAmount;
    @NonNull
    private String outputCurrency;

    public void setOutputAmount() {
        this.outputAmount = 0;
    }
}
