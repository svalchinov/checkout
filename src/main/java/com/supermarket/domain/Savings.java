package com.supermarket.domain;


import java.math.BigDecimal;

/**
 * Interface to be implemented by classes providing a price saving API
 */
public interface Savings {

    /**
     * Calculate and return the savings amount
     * @return a {@code BigDecimal} whose value with the savings amount
     */
    BigDecimal getSavings();
}
