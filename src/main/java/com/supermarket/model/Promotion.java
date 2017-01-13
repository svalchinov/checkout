package com.supermarket.model;

import java.math.BigDecimal;
import java.util.Date;

import static com.supermarket.constants.Rounding.SCALE;

public class Promotion {

    private String name;
    private int quantity;
    private BigDecimal discount;
    private Date expiryDate;

    public Promotion(String name,
                     int quantity,
                     BigDecimal discount) {

        this.name = name;
        this.quantity = quantity;
        this.discount = discount;
    }

    public String getName() {
        return name;
    }

    public int getQuantity() {
        return quantity;
    }

    public Date getExpiryDate() {
        return expiryDate;
    }

    public BigDecimal getDiscount() {
        return discount.setScale(SCALE);
    }
}
