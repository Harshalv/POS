package com.shop.Promotions;

import com.shop.exception.CheckoutException;

/**
 * Implements the Buy 1 get 1 free offer
 */
public class Bogof implements Offer {
    public String getName() {
        return "Buy 1 get 1 free";
    }

    /**
     * Calculates the total price as per the offer
     * @param quantity - no of items
     * @param itemUnitPrice - unit price of the item
     * @return total price of the items as per the offer
     */
    public Double getTotalPrice(int quantity, Double itemUnitPrice) throws CheckoutException {
        if(itemUnitPrice == null){
            throw new CheckoutException("item unit price can not be null");
        }

        return (quantity /2 + quantity % 2 ) * itemUnitPrice;
    }
}
