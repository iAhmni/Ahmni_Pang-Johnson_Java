package com.company.gameStore.models;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.validation.constraints.Digits;
import javax.validation.constraints.NotNull;
import java.math.BigDecimal;

@Entity
@Table(name = "tax")
public class Tax {
    @Id
    private String state;
    @NotNull(message = "You must provide a tax.")
    @Digits(integer = 8, fraction = 2, message = "Price must be a number with up to 2 decimal places.")
    private BigDecimal rate;

    public String getState() {
        return state;
    }

    public void setState(String state) {
        this.state = state;
    }

    public BigDecimal getRate() {
        return rate;
    }

    public void setRate(BigDecimal rate) {
        this.rate = rate;
    }
}
