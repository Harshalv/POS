package com.shop.Promotions;

import com.shop.exception.CheckoutException;

/**
 * implements the 3 for 2 offer
 */
public class ThreeForTwo implements Offer{
    public String getName() {
        return "3 for 2";
    }

    public Double getTotalPrice(int quantity, Double itemUnitPrice) throws CheckoutException{
        if(itemUnitPrice == null){
            throw new CheckoutException("item unit price can not be null");
        }
        //calculate the price of lots of 3 at the price of 2 and add the remaining items at unit price
        return ((quantity/3) * (itemUnitPrice*2) ) + ((quantity % 3) * itemUnitPrice);
    }
}
