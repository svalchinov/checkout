package com.supermarket.domain;


import com.supermarket.model.Item;
import com.supermarket.model.Promotion;
import org.junit.Test;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

import static java.util.Arrays.asList;
import static org.hamcrest.CoreMatchers.instanceOf;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNull;
import static org.junit.Assert.assertThat;

public class PurchaseTest {

    @Test
    public void purchase_implementsSavingsInterface() {
        Item item = new Item("Cookies", new BigDecimal("1.50"), new ArrayList<Promotion>());
        assertThat(new Purchase(item), instanceOf(Savings.class));
    }

    @Test
    public void getTotal() {

        // given
        Item item = new Item("Cookies", new BigDecimal("1.50"), new ArrayList<Promotion>());
        Purchase purchase = new Purchase(item);
        purchase.setQuantity(3);

        // when
        BigDecimal result = purchase.getTotal();

        // then
        assertEquals("4.50", result.toString());
    }

    @Test
    public void getName_returnsItemName() {

        // given
        Item item = new Item("Cookies", new BigDecimal("1.50"), new ArrayList<Promotion>());

        // when
        Purchase purchase = new Purchase(item);

        // then
        assertEquals(item.getName(), purchase.getName());
    }


    @Test
    public void getSavings_zeroForNoPromotions() {

        // given
        Item item = new Item("Cookies", new BigDecimal("1.50"), new ArrayList<Promotion>());
        Purchase purchase = new Purchase(item);
        purchase.setQuantity(3);

        // when
        BigDecimal savings = purchase.getSavings();

        // then
        assertEquals("0", savings.toString());
    }

    @Test
    public void getSavings_zeroForIneligiblePromotions() {

        // given
        BigDecimal singleItemPrice = new BigDecimal("1.50");
        Promotion twoForOne = new Promotion("2 for 1", 2, singleItemPrice, singleItemPrice);
        List<Promotion> promotions = asList(twoForOne);
        Item item = new Item("Cookies", singleItemPrice, promotions);
        Purchase purchase = new Purchase(item);

        // when
        BigDecimal savings = purchase.getSavings();

        // then
        assertEquals("0", savings.toString());
    }

    @Test
    public void getSavings_applyPromoOnce() {

        // given
        BigDecimal singleItemPrice = new BigDecimal("1.50");
        Promotion twoForOne = new Promotion("2 for 1", 2, singleItemPrice, singleItemPrice);
        List<Promotion> promotions = asList(twoForOne);
        Item item = new Item("Cookies", singleItemPrice, promotions);
        Purchase purchase = new Purchase(item);
        purchase.setQuantity(2);

        // when
        BigDecimal savings = purchase.getSavings();

        // then
        assertEquals(singleItemPrice, savings);
    }

    @Test
    public void getSavings_applyPromoMultipleTimes() {

        // given
        BigDecimal singleItemPrice = new BigDecimal("1.50");
        Promotion twoForOne = new Promotion("2 for 1", 2, singleItemPrice, singleItemPrice);
        List<Promotion> promotions = asList(twoForOne);
        Item item = new Item("Cookies", singleItemPrice, promotions);
        Purchase purchase = new Purchase(item);
        purchase.setQuantity(10);

        // when
        BigDecimal savings = purchase.getSavings();

        // then
        assertEquals("7.50", savings.toString());
    }

    @Test
    public void getSavings_twoForLess() {

        // given
        Promotion twoForLess = new Promotion("2 For Less", 2, new BigDecimal("2.00"), new BigDecimal("0.7"));
        List<Promotion> promotions = asList(twoForLess);
        Item item = new Item("Cookies", new BigDecimal("1.35"), promotions);
        Purchase purchase = new Purchase(item);
        purchase.setQuantity(2);

        // when
        BigDecimal savings = purchase.getSavings();

        // then
        assertEquals("0.70", savings.toString());
    }

    @Test
    public void getBestPromotion_forSameQuantity() {

        // given
        Promotion twoForOne = new Promotion("2 for 1", 2, new BigDecimal("1.50"), new BigDecimal("1.50"));
        Promotion twoForLess = new Promotion("2 for less", 2, new BigDecimal("2.50"), new BigDecimal("0.50"));
        List<Promotion> promotions = asList(twoForOne, twoForLess);
        Item item = new Item("Cookies", new BigDecimal("1.50"), promotions);

        // when
        Purchase purchase = new Purchase(item);
        purchase.setQuantity(2);

        // then
        assertEquals(twoForOne.getName(), purchase.getBestPromotion().getName());
        assertEquals(twoForOne.getSavings(), purchase.getBestPromotion().getSavings());
    }

    @Test
    public void getBestPromotion_differentQuantityPromotion() {

        // given
        Promotion twoForOne = new Promotion("2 for 1", 2, new BigDecimal("1.50"), new BigDecimal("1.50"));
        Promotion threeForLess = new Promotion("3 for less", 2, new BigDecimal("2.50"), new BigDecimal("0.50"));
        List<Promotion> promotions = asList(twoForOne, threeForLess);
        Item item = new Item("Cookies", new BigDecimal("1.50"), promotions);

        // when
        Purchase purchase = new Purchase(item);
        purchase.setQuantity(2);

        // then
        assertEquals(twoForOne.getName(), purchase.getBestPromotion().getName());
        assertEquals(twoForOne.getSavings(), purchase.getBestPromotion().getSavings());
    }

    @Test
    public void getBestPromotion_itemNotEligibleForPromotion() {

        // given
        Promotion twoForOne = new Promotion("2 for 1", 2, new BigDecimal("1.50"), new BigDecimal("1.50"));
        List<Promotion> promotions = asList(twoForOne);
        Item item = new Item("Cookies", new BigDecimal("1.50"), promotions);

        // when
        Purchase purchase = new Purchase(item);

        // then
        assertNull(purchase.getBestPromotion());
    }

    @Test
    public void purchase_toString() {

        // given
        Item item = new Item("Cookies", new BigDecimal("1.50"), new ArrayList<Promotion>());

        // when
        Purchase purchase = new Purchase(item);

        // then
        assertEquals("Cookies x1: 1.50", purchase.toString());
    }


}