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

    public Promotion getBestPromotion() {

        Promotion bestPromotion = null;
        BigDecimal discount = ZERO;

        for (Promotion promotion : item.getPromotions()) {
            if (isPromotionDiscountBetter(promotion, discount)) {
                bestPromotion = promotion;
                discount = promotion.getDiscount();
            }
        }

        return bestPromotion;
    }

    @Override
    public BigDecimal getSavings() {

        Promotion promotion = getBestPromotion();

        if (promotion != null && quantity >= promotion.getQuantity()) {
            BigDecimal discount = promotion.getDiscount();
            int timesPromotionCanBeApplied = quantity / promotion.getQuantity();
            return discount.multiply(valueOf(timesPromotionCanBeApplied));
        }

        return ZERO;
    }

    @Override
    public String toString() {
        return item.getName() + " x" + quantity + ": " + item.getPrice();
    }


    private boolean isPromotionDiscountBetter(Promotion promotion, BigDecimal discount) {
        return quantity >= promotion.getQuantity() && promotion.getDiscount().compareTo(discount) >= 0;
    }
}
