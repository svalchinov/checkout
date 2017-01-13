package com.supermarket.service;

import com.supermarket.domain.Basket;
import com.supermarket.model.Item;
import com.supermarket.model.Promotion;
import org.junit.Before;
import org.junit.Test;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import java.math.BigDecimal;
import java.util.ArrayList;

import static org.mockito.Mockito.verify;

public class CheckoutServiceTest {

    @Mock
    private Basket basket;

    private CheckoutService checkoutService;

    @Before
    public void before() {
        MockitoAnnotations.initMocks(this);
        this.checkoutService = new CheckoutService(basket);
    }

    @Test
    public void scan_willDelegate() {

        // given
        Item cookies = new Item("Cookies", new BigDecimal(0.75), new ArrayList<Promotion>());

        // when
        checkoutService.scan(cookies);

        // then
        verify(basket).addPurchase(cookies);
    }

    @Test
    public void removeItem_willDelegate() {

        // given
        Item cookies = new Item("Cookies", new BigDecimal(0.75), new ArrayList<Promotion>());
        checkoutService.scan(cookies);

        // when
        checkoutService.removeItem(cookies);

        // then
        verify(basket).removePurchase(cookies);
    }

}