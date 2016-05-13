package com.shop;

import com.shop.exception.CheckoutException;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * The Checkout manager class manages the item consolidation and billing
 */
public class CheckoutManager {

    //Map of items and their prices
    Map<String,Double> itemPrices;

    public CheckoutManager(Map<String,Double> itemPrices) {
        if(itemPrices == null){
            throw new IllegalArgumentException("CheckoutManager needs Item prices to complete checkout");
        }
        this.itemPrices = itemPrices;
    }

    /**
     * Calculates the total price of the checkout
     * @param items - items in the checkout
     * @return - Double - total price of the checkout
     * @throws IllegalArgumentException - If the items provided at checkout are null or empty
     * @throws CheckoutException -  if any item does not have a price
     */
    public Double getTotalPrice(List<String> items) throws CheckoutException{
        if(items == null || items.isEmpty()) {
            throw new IllegalArgumentException("No items provided for checkout");
        }
        Double totalPrice = 0.0;
        for(String item : items){
            Double itemPrice = itemPrices.get(item);
            if(itemPrice == null){
                throw new CheckoutException("Item "+item+" does not have a price");
            }
            totalPrice += itemPrice;
        }
        return totalPrice;
    }

}
