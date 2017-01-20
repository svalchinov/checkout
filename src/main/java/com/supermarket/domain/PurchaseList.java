package com.supermarket.domain;


import com.supermarket.model.Item;

import java.util.HashMap;
import java.util.Map;

public class PurchaseList {

    private HashMap<String, Purchase> purchases;

    public PurchaseList() {
        purchases =  new HashMap<String, Purchase>();
    }

    public Map<String, Purchase> getPurchases() {
        return purchases;
    }

    public void add(Item item) {

        if (isItemScanned(item)) {
            Purchase current = purchases.get(item.getName());
            current.setQuantity(current.getQuantity() + 1);
            purchases.put(item.getName(), current);
        } else {
            purchases.put(item.getName(), new Purchase(item));
        }
    }

    public void remove(Item item) {
        if (isItemScanned(item) && !isItemSingleQuantity(item)) {
            Purchase current = purchases.get(item.getName());
            current.setQuantity(current.getQuantity() - 1);
        } else {
            purchases.remove(item.getName());
        }
    }


    private boolean isItemSingleQuantity(Item item) {
        return purchases.get(item.getName()).getQuantity() == 1;
    }

    private boolean isItemScanned(Item item) {
        return purchases.get(item.getName()) != null;
    }
}
