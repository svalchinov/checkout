package com.supermarket.domain;


import com.supermarket.model.Item;
import com.supermarket.model.Promotion;
import org.junit.Before;
import org.junit.Test;

import java.math.BigDecimal;
import java.util.ArrayList;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

public class PurchaseListTest {

    private PurchaseList purchaseList;

    @Before
    public void setup() {
        purchaseList = new PurchaseList();
    }

    @Test
    public void purchaseList_hasDefaultEmptyMap() {
        assertTrue(purchaseList.getPurchases().isEmpty());
    }

    @Test
    public void add() {

        // given
        Item milk = new Item("Milk", new BigDecimal("1.57"), new ArrayList<Promotion>());
        Item cookies = new Item("Cookies", new BigDecimal("0.75"), new ArrayList<Promotion>());

        // when
        purchaseList.add(milk);
        purchaseList.add(cookies);

        // then
        assertEquals(2, purchaseList.getPurchases().size());
        assertEquals(milk, purchaseList.getPurchases().get(milk.getName()).getItem());
        assertEquals(cookies, purchaseList.getPurchases().get(cookies.getName()).getItem());
    }

    @Test
    public void add_existingItem() {

        // given
        Item milk = new Item("Milk", new BigDecimal("1.57"), new ArrayList<Promotion>());
        Item cookies = new Item("Cookies", new BigDecimal("0.75"), new ArrayList<Promotion>());

        // when
        purchaseList.add(milk);
        purchaseList.add(cookies);
        purchaseList.add(milk);

        // then
        assertEquals(2, purchaseList.getPurchases().size());
        assertEquals(2, purchaseList.getPurchases().get(milk.getName()).getQuantity());
        assertEquals(milk, purchaseList.getPurchases().get(milk.getName()).getItem());
    }


    @Test
    public void removePurchase_emptyBasket() {

        // given
        Item milk = new Item("Milk", new BigDecimal("1.57"), new ArrayList<Promotion>());

        // when
        purchaseList.remove(milk);

        // then
        assertTrue(purchaseList.getPurchases().isEmpty());
    }

    @Test
    public void removePurchase_existingItem() {

        // given
        Item milk = new Item("Milk", new BigDecimal("1.57"), new ArrayList<Promotion>());
        purchaseList.add(milk);

        // when
        purchaseList.remove(milk);

        // then
        assertTrue(purchaseList.getPurchases().isEmpty());
    }

    @Test
    public void removePurchase_existingItemMultipleQuantity() {

        // given
        Item milk = new Item("Milk", new BigDecimal("1.57"), new ArrayList<Promotion>());
        Item cookies = new Item("Cookies", new BigDecimal("0.75"), new ArrayList<Promotion>());
        purchaseList.add(milk);
        purchaseList.add(cookies);
        purchaseList.add(milk);
        purchaseList.add(milk);

        // when
        purchaseList.remove(milk);

        // then
        assertEquals(2, purchaseList.getPurchases().size());
        assertEquals(2, purchaseList.getPurchases().get(milk.getName()).getQuantity());
    }

    @Test
    public void removePurchase_removingLastItem() {

        // given
        Item milk = new Item("Milk", new BigDecimal("1.57"), new ArrayList<Promotion>());
        Item cookies = new Item("Cookies", new BigDecimal("0.75"), new ArrayList<Promotion>());
        purchaseList.add(milk);
        purchaseList.add(cookies);
        purchaseList.add(milk);

        // when
        purchaseList.remove(milk);
        purchaseList.remove(milk);

        // then
        assertEquals(1, purchaseList.getPurchases().size());
        assertEquals(cookies, purchaseList.getPurchases().get(cookies.getName()).getItem());
    }
}