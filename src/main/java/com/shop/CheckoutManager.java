package com.shop;

import com.shop.Promotions.Offer;
import com.shop.exception.CheckoutException;

import java.util.*;

/**
 * The Checkout manager class manages the item consolidation and billing
 */
public class CheckoutManager {

    //Map of items and their prices
    private Map<String,Double> itemPrices;
    private Map<String,Offer> itemOffers;

    public CheckoutManager(Map<String,Double> itemPrices,Map<String,Offer> itemOffers) {
        if(itemPrices == null){
            throw new IllegalArgumentException("CheckoutManager needs Item prices to complete checkout");
        }
        this.itemPrices = itemPrices;

        if(itemOffers == null){
            // assuming we continue if we don't have offers
            itemOffers = new TreeMap<>();
        }
        this.itemOffers = itemOffers;
    }

    /**
     * Calculates the total price of the checkout
     * @param items - items in the checkout
     * @return - Double - total price of the checkout
     * @throws IllegalArgumentException - If the items provided at checkout are null or empty
     * @throws CheckoutException -  if any item does not have a price
     */
    public Double getTotalPrice(List<String> items) throws CheckoutException{

        Map<String,Integer> consolidatedBasket = getConsolidatedBasket(items);
        Double totalPrice = 0.0;
        Set<String> uniqueItems = consolidatedBasket.keySet();
        for(String item : uniqueItems){
            Double itemPrice = itemPrices.get(item);
            if(itemPrice == null){
                throw new CheckoutException("Item "+item+" does not have a price");
            }

            Offer offer = itemOffers.get(item);
            if(offer!=null){
                Double offerPrice = offer.getTotalPrice(consolidatedBasket.get(item),itemPrice);
                totalPrice += offerPrice;
            } else {
                totalPrice += consolidatedBasket.get(item)*itemPrice;
            }
        }
        return totalPrice;
    }

    /**
     * consolidates items to their quantities
     * @param items - list of items to consolidate
     * @return consolidated list of items
     */
    private Map<String, Integer> getConsolidatedBasket(List<String> items) {
        Map<String, Integer> consolidatedBasket = new TreeMap<>(String.CASE_INSENSITIVE_ORDER);
        if(items == null || items.isEmpty()) {
            throw new IllegalArgumentException("No items provided for checkout");
        }

        for(String item : items){
            Integer quantity = consolidatedBasket.get(item);
            if(quantity == null){
                quantity = 0;
            }
            consolidatedBasket.put(item,quantity+1);
        }
        return consolidatedBasket;
    }

}
