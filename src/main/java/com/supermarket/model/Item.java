package com.supermarket.model;


import java.math.BigDecimal;
import java.util.List;

import static com.supermarket.constants.Rounding.SCALE;

public class Item {

    private String name;
    private BigDecimal price;
    private List<Promotion> promotions;

    public Item(String name,
                BigDecimal price,
                List<Promotion> promotions) {

        this.name = name;
        this.price = price;
        this.promotions = promotions;
    }

    public String getName() {
        return name;
    }

    public BigDecimal getPrice() {
        return price.setScale(SCALE);
    }

    public List<Promotion> getPromotions() {
        return promotions;
    }

    public void addPromotion(Promotion promotion) {
        this.promotions.add(promotion);
    }

    public void removePromotion(Promotion promotion) {
        this.promotions.remove(promotion);
    }
}
