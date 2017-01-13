package com.supermarket.service;


import com.supermarket.domain.Basket;
import com.supermarket.model.Item;

public class CheckoutService {

    private Basket basket;

    public CheckoutService(Basket basket) {
        this.basket = basket;
    }

    public void scan(Item item) {
        basket.addPurchase(item);
        printResult();
    }

    public void removeItem(Item item) {
        basket.removePurchase(item);
        printResult();
    }

    public void checkout(Basket basket) {

        printResult();
    }


    private void printResult() {

        System.out.println();
        System.out.println(basket.getPurchases().values());
        System.out.println("--------------------");
        System.out.println("Price: " + basket.getBasketPrice());
        System.out.println("Savings: " + basket.getSavings());
        System.out.println("Total: " + basket.getBasketTotal());
    }
}
