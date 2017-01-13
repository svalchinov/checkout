package com.supermarket.domain;


import com.supermarket.model.Item;
import com.supermarket.model.Promotion;

import java.math.BigDecimal;

import static java.math.BigDecimal.ZERO;
import static java.math.BigDecimal.valueOf;

public class Purchase implements Savings {

    private Item item;
    private int quantity;

    public Purchase(Item item) {
        this.item = item;
        this.quantity = 1;
    }

    public Item getItem() {
        return item;
    }

    public BigDecimal getTotal() {
        return item.getPrice().multiply(BigDecimal.valueOf(quantity));
    }

    public String getName() {
        return item.getName();
    }

    public void setQuantity(int quantity) {
        this.quantity = quantity;
    }

    public int getQuantity() {
        return this.quantity;
    }

    /**
     * Implement more clever promotion selection mechanism where
     * We run through Purchase checking which and how many promotions can be applied
     */
    @Override
    public BigDecimal getSavings() {

        Promotion promotion = getBestPromotion();

        if (promotion != null && quantity >= promotion.getQuantity()) {
            BigDecimal cost = promotion.getSavings();
            int timesToApplyPromo = quantity / promotion.getQuantity();
            return cost.multiply(valueOf(timesToApplyPromo));
        }

        return ZERO;
    }

    public Promotion getBestPromotion() {

        Promotion promotion = null;
        BigDecimal discount = ZERO;

        for (Promotion promo : item.getPromotions()) {
            if (isItemEligible(promo) && promo.getSavings().compareTo(discount) >= 0) {
                promotion = promo;
                discount = promo.getSavings();
            }
        }

        return promotion;
    }

    private boolean isItemEligible(Promotion promotion) {
        return quantity >= promotion.getQuantity();
    }


    @Override
    public String toString() {
        return item.getName() + " x" + quantity + ": " + item.getPrice();
    }
}
