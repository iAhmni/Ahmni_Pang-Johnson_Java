package com.company.gameStore.models;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.validation.constraints.Digits;
import javax.validation.constraints.NotNull;
import java.math.BigDecimal;

@Entity
@Table(name = "fee")
public class Fee {

    @Id
    @Column(name = "product_type")
    private String productType;
    @NotNull(message = "You must provide a tax.")
    @Digits(integer = 8, fraction = 2, message = "Price must be a number with up to 2 decimal places.")
    private BigDecimal fee;

    public String getProductType() {
        return productType;
    }

    public void setProductType(String productType) {
        this.productType = productType;
    }

    public BigDecimal getFee() {
        return fee;
    }

    public void setFee(BigDecimal fee) {
        this.fee = fee;
    }
}
