package com.shop;

import com.shop.exception.CheckoutException;
import org.junit.Before;
import org.junit.Test;

import java.util.*;

import static org.junit.Assert.*;

/**
 * Created by Luther on 12/05/2016.
 */
public class CheckoutManagerTest {
    CheckoutManager checkoutManager;

    @Before
    public void setUp() throws Exception {
        Map<String,Double> itemPrices = new TreeMap<String, Double>(String.CASE_INSENSITIVE_ORDER);
        itemPrices.put("Apple",0.60);
        itemPrices.put("Orange",0.25);

        checkoutManager = new CheckoutManager(itemPrices);
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
    public void getTotalPrice4Items() throws Exception {
        Double totalPrice = checkoutManager.getTotalPrice(Arrays.asList("Apple","Orange","Apple","orange"));

        assertEquals("Price should be 1.70",new Double(1.70),totalPrice);
    }

    @Test
    public void getTotalPriceOnlyApples() throws Exception {
        Double totalPrice = checkoutManager.getTotalPrice(Arrays.asList("Apple","Apple","Apple","Apple"));

        assertEquals("Price should be 2.40",new Double(2.40),totalPrice);
    }

    @Test
    public void getTotalPriceOnlyOranges() throws Exception {
        Double totalPrice = checkoutManager.getTotalPrice(Arrays.asList("Orange","orange","Orange","Orange"));

        assertEquals("Price should be 1.0",new Double(1.0),totalPrice);
    }
}