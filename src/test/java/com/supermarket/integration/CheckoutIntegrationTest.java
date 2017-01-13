package com.supermarket.integration;

import com.supermarket.domain.Basket;
import com.supermarket.model.Item;
import com.supermarket.model.Promotion;
import com.supermarket.service.CheckoutService;
import org.junit.Before;
import org.junit.Test;
import org.mockito.MockitoAnnotations;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Arrays;

public class CheckoutIntegrationTest {

    private CheckoutService checkoutService;

    @Before
    public void before() {
        MockitoAnnotations.initMocks(this);
        this.checkoutService = new CheckoutService(new Basket());
    }

    @Test
    public void scan() {

        // given
        Item cookies = new Item("Cookies", new BigDecimal(0.75), new ArrayList<Promotion>());
        Item milk = new Item("Milk", new BigDecimal(1.35), new ArrayList<Promotion>());

        // when
        checkoutService.scan(cookies);
        checkoutService.scan(milk);
        checkoutService.scan(milk);
        checkoutService.scan(milk);
    }


    @Test
    public void scan_withSinglePromotion() {

        // given
        Item milk = new Item("Milk", new BigDecimal("1.35"), new ArrayList<Promotion>());
        Promotion twoForOne = new Promotion("2 For 1", 2, new BigDecimal("2.50"), new BigDecimal(2.50));
        Item cake = new Item("Cake", new BigDecimal("2.50"), Arrays.asList(twoForOne));

        // when
        checkoutService.scan(milk);
        checkoutService.scan(cake);
        checkoutService.scan(cake);
    }

    @Test
    public void scan_multiplePromotions() {

        // given
        Promotion twoForLess = new Promotion("2 For Less", 2, new BigDecimal("2.00"), new BigDecimal("0.70"));
        Promotion twoForOne = new Promotion("2 For 1", 2, new BigDecimal("2.50"), new BigDecimal("2.50"));

        Item milk = new Item("Milk", new BigDecimal("1.35"), Arrays.asList(twoForLess));
        Item cake = new Item("Cake", new BigDecimal("2.50"), Arrays.asList(twoForOne));

        // when
        checkoutService.scan(milk);
        checkoutService.scan(milk);
        checkoutService.scan(cake);
        checkoutService.scan(cake);

    }
}