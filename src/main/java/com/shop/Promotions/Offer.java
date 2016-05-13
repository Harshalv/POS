package com.shop.Promotions;

import com.shop.exception.CheckoutException;

/**
 * interface to define Offers functionality
 */
public interface Offer {
    /**
     * gets the name of the offer
     * @return - Name of the offer
     */
    String getName();

    /**
     * Calculates the total price as per the offer
     * @param quantity - no of items
     * @param itemUnitPrice - unit price of the item
     * @return total price of the items as per the offer
     * @throws CheckoutException - if the item unit price is null
     */
    Double getTotalPrice(int quantity,Double itemUnitPrice) throws CheckoutException;
}
