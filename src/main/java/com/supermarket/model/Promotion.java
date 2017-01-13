package com.supermarket.model;

import java.math.BigDecimal;
import java.util.Date;

import static com.supermarket.constants.Rounding.SCALE;

public class Promotion {

    private String name;
    private int quantity;
    private BigDecimal cost;
    private BigDecimal savings;
    private Date expiryDate;

    public Promotion(String name,
                     int quantity,
                     BigDecimal cost,
                     BigDecimal savings) {

        this.name = name;
        this.quantity = quantity;
        this.cost = cost;
        this.savings = savings;
    }

    public String getName() {
        return name;
    }

    public int getQuantity() {
        return quantity;
    }

    public BigDecimal getCost() {
        return cost;
    }

    public Date getExpiryDate() {
        return expiryDate;
    }

    public BigDecimal getSavings() {
        return savings.setScale(SCALE);
    }
}