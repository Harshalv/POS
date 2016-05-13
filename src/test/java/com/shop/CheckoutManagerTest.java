package com.shop;

import com.shop.Promotions.Bogof;
import com.shop.Promotions.Offer;
import com.shop.Promotions.ThreeForTwo;
import com.shop.exception.CheckoutException;
import org.junit.Before;
import org.junit.Test;

import java.util.*;

import static org.junit.Assert.*;

/**
 * Test class for CheckoutManager
 */
public class CheckoutManagerTest {
    CheckoutManager checkoutManager;
    Map<String,Double> itemPrices;

    @Before
    public void setUp() throws Exception {
        itemPrices = new TreeMap<String, Double>(String.CASE_INSENSITIVE_ORDER);
        itemPrices.put("Apple",0.60);
        itemPrices.put("Orange",0.25);

        Map<String,Offer> itemOffers = new TreeMap<>(String.CASE_INSENSITIVE_ORDER);
        itemOffers.put("Apple",new Bogof());
        itemOffers.put("Orange",new ThreeForTwo());


        checkoutManager = new CheckoutManager(itemPrices,itemOffers);
    }

    @Test(expected = IllegalArgumentException.class)
    public void getTotalPriceNoItems() throws Exception {
        Double totalPrice = checkoutManager.getTotalPrice(new ArrayList<String>());
    }

    @Test(expected = IllegalArgumentException.class)
    public void getTotalPriceNullItems() throws Exception {
        Double totalPrice = checkoutManager.getTotalPrice(null);
    }

    @Test(expected = CheckoutException.class)
    public void getTotalPriceItemWithNoPrice() throws Exception {
        Double totalPrice = checkoutManager.getTotalPrice(Arrays.asList("Banana"));
    }

    @Test
    public void getTotalPrice2ItemsWithoutOffer() throws Exception {
        Double totalPrice = checkoutManager.getTotalPrice(Arrays.asList("Apple","Orange"));

        assertEquals("Price should be 0.85",new Double(0.85),totalPrice);
    }

    @Test
    public void getTotalPriceOn4Apples() throws Exception {
        Double totalPrice = checkoutManager.getTotalPrice(Arrays.asList("Apple","Apple","Apple","Apple"));

        assertEquals("Price should be 1.20",new Double(1.20),totalPrice);
    }

    @Test
    public void getTotalPriceOn4Oranges() throws Exception {
        Double totalPrice = checkoutManager.getTotalPrice(Arrays.asList("Orange","orange","Orange","Orange"));

        assertEquals("Price should be 0.75",new Double(0.75),totalPrice);
    }

    @Test
    public void getTotalPriceOn4Apple4Oranges() throws Exception {
        Double totalPrice = checkoutManager.getTotalPrice(Arrays.asList("Orange","orange","Orange","Orange","Apple","Apple","Apple","Apple"));

        assertEquals("Price should be 1.95",new Double(1.95),totalPrice);
    }

    @Test
    public void getTotalPriceOn4Apple4OrangesWithoutOffers() throws Exception {
        CheckoutManager checkoutManagerWithoutOffers = new CheckoutManager(itemPrices,null);
        Double totalPrice = checkoutManagerWithoutOffers.getTotalPrice(Arrays.asList("Orange","orange","Orange","Orange","Apple","Apple","Apple","Apple"));

        assertEquals("Price should be 3.40",new Double(3.40),totalPrice);
    }

    @Test(expected = IllegalArgumentException.class)
    public void getTotalPriceOn4Apple4OrangesWithoutOffersWithoutPrices() throws Exception {
        CheckoutManager checkoutManagerWithoutOffers = new CheckoutManager(null,null);
    }
}