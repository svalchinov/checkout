package com.supermarket.domain;

import com.supermarket.model.Item;

import java.math.BigDecimal;
import java.util.HashMap;
import java.util.Map;

import static com.supermarket.constants.Rounding.SCALE;
import static java.math.BigDecimal.ZERO;

public class Basket implements Savings {

    private HashMap<String, Purchase> purchases = new HashMap<String, Purchase>();

    public void addPurchase(final Item item) {

        if (isItemScanned(item)) {
            Purchase current = purchases.get(item.getName());
            current.setQuantity(current.getQuantity() + 1);
            purchases.put(item.getName(), current);

        } else {
            purchases.put(item.getName(), new Purchase(item));
        }
    }

    public void removePurchase(final Item item) {

        if (isItemScanned(item) && !isItemSingleQuantity(item)) {
            Purchase current = purchases.get(item.getName());
            current.setQuantity(current.getQuantity() - 1);
        } else {
            purchases.remove(item.getName());
        }
    }

    public BigDecimal getBasketPrice() {

        BigDecimal total = ZERO;

        for (Map.Entry entry : purchases.entrySet()) {
            Purchase purchase = (Purchase) entry.getValue();
            total = total.add(purchase.getTotal());
        }

        return total.setScale(SCALE);
    }

    public BigDecimal getBasketTotal() {
        return getBasketPrice().subtract(getSavings()).setScale(SCALE);
    }

    public Map<String, Purchase> getPurchases() {
        return purchases;
    }

    @Override
    public BigDecimal getSavings() {

        BigDecimal totalSavings = ZERO;

        for (Map.Entry entry : purchases.entrySet()) {
            Purchase purchase = (Purchase) entry.getValue();
            totalSavings = totalSavings.add(purchase.getSavings());
        }
        return totalSavings.setScale(SCALE);
    }

    private boolean isItemSingleQuantity(Item item) {
        return purchases.get(item.getName()).getQuantity() == 1;
    }

    private boolean isItemScanned(Item item) {
        return purchases.get(item.getName()) != null;
    }
}

