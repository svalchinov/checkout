package com.supermarket.domain;

import com.supermarket.model.Item;

import java.math.BigDecimal;
import java.util.Map;

import static com.supermarket.constants.Rounding.SCALE;
import static java.math.BigDecimal.ZERO;

public class Basket implements Savings {

    private PurchaseList purchases;

    public Basket(PurchaseList purchases) {
        this.purchases = purchases;
    }

    public void addPurchase(final Item item) {
        purchases.add(item);
    }

    public void removePurchase(final Item item) {
        purchases.remove(item);
    }

    public BigDecimal getBasketPrice() {

        BigDecimal total = ZERO;

        for (Map.Entry entry : purchases.getPurchases().entrySet()) {
            Purchase purchase = (Purchase) entry.getValue();
            total = total.add(purchase.getTotal());
        }

        return total.setScale(SCALE);
    }

    public BigDecimal getBasketTotal() {
        return getBasketPrice().subtract(getSavings()).setScale(SCALE);
    }

    public Map<String, Purchase> getPurchases() {
        return purchases.getPurchases();
    }


    @Override
    public BigDecimal getSavings() {

        BigDecimal totalSavings = ZERO;

        for (Map.Entry entry : purchases.getPurchases().entrySet()) {
            Purchase purchase = (Purchase) entry.getValue();
            totalSavings = totalSavings.add(purchase.getSavings());
        }
        return totalSavings.setScale(SCALE);
    }
}

