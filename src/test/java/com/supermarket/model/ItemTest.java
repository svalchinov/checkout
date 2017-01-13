package com.supermarket.model;

import org.junit.Test;

import java.math.BigDecimal;
import java.util.ArrayList;

import static java.math.BigDecimal.ROUND_FLOOR;
import static java.math.BigDecimal.TEN;
import static org.junit.Assert.assertEquals;

public class ItemTest {


    @Test
    public void getPrice() {
        assertEquals(TEN.setScale(2, ROUND_FLOOR), new Item("Item", TEN, new ArrayList<Promotion>()).getPrice());
    }

    @Test
    public void addPromotion() {

        // given
        Item milk = new Item("Milk", new BigDecimal(1.57), new ArrayList<Promotion>());
        Promotion twoForOne = new Promotion("2 for 1", 2, new BigDecimal(0.75));

        // when
        milk.addPromotion(twoForOne);

        // then
        assertEquals(1, milk.getPromotions().size());
    }

    @Test
    public void removePromotion() {

        // given
        Item milk = new Item("Milk", new BigDecimal(1.57), new ArrayList<Promotion>());
        Promotion twoForOne = new Promotion("2 for 1", 2, new BigDecimal(0.75));
        milk.addPromotion(twoForOne);

        // when
        milk.removePromotion(twoForOne);

        // then
        assertEquals(0, milk.getPromotions().size());
    }
}