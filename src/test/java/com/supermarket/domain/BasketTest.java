package com.supermarket.domain;

import com.supermarket.model.Item;
import com.supermarket.model.Promotion;
import org.junit.Before;
import org.junit.Test;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Arrays;

import static java.math.BigDecimal.*;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

public class BasketTest {

    private Basket basket;

    @Before
    public void setup() {
        this.basket = new Basket(new PurchaseList());
    }

    @Test
    public void purchases_isEmptyByDefaultNotNull() {
        assertTrue(basket.getPurchases().isEmpty());
    }

    @Test
    public void getBasketPrice_basketWithNoPurchases() {
        assertEquals("0.00", basket.getBasketPrice().toString());
    }

    @Test
    public void getBasketPrice_fullBasket() {

        // given
        Item milk = new Item("Milk", new BigDecimal("1.57"), new ArrayList<Promotion>());
        Item cookies = new Item("Cookies", new BigDecimal("0.75"), new ArrayList<Promotion>());

        // when
        basket.addPurchase(milk);
        basket.addPurchase(cookies);

        // then
        assertEquals("2.32", basket.getBasketPrice().toString());
    }

    @Test
    public void getBasketTotal_noSavings() {

        // given
        Item milk = new Item("Milk", new BigDecimal("1.57"), new ArrayList<Promotion>());
        Item cookies = new Item("Cookies", new BigDecimal("0.75"), new ArrayList<Promotion>());

        // when
        basket.addPurchase(milk);
        basket.addPurchase(cookies);

        // then
        assertEquals("2.32", basket.getBasketTotal().toString());
    }

    @Test
    public void getBasketTotal_savings() {

        // given
        Item milk = new Item("Milk", new BigDecimal("1.57"), new ArrayList<Promotion>());
        Promotion twoForOne = new Promotion("2 for 1", 2, new BigDecimal("0.75"));
        Item cookies = new Item("Cookies", new BigDecimal("0.75"), Arrays.asList(twoForOne));

        // when
        basket.addPurchase(milk);
        basket.addPurchase(cookies);
        basket.addPurchase(cookies);

        // then
        assertEquals("2.32", basket.getBasketTotal().toString());
    }

    @Test
    public void getSavings_emptyBasket() {
        assertEquals(ZERO.setScale(2), basket.getSavings());
    }

    @Test
    public void getSavings_fullBasket() {

        // given
        BigDecimal singleItemPrice = new BigDecimal("1.50").setScale(2);
        Promotion twoForOne = new Promotion("2 for 1", 2, singleItemPrice);
        Item milk = new Item("Milk", singleItemPrice, Arrays.asList(twoForOne));
        Item cookies = new Item("Cookies", new BigDecimal(2), new ArrayList<Promotion>());

        // when
        basket.addPurchase(milk);
        basket.addPurchase(cookies);
        basket.addPurchase(milk);

        // then
        assertEquals(singleItemPrice, basket.getSavings());
    }
}