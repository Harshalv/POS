package com.shop.Promotions;

import com.shop.exception.CheckoutException;
import org.junit.Test;

import static org.junit.Assert.*;

/**
 * Test class for ThreeForTwo
 */
public class ThreeForTwoTest {
    @Test
    public void getTotalPrice4Items() throws Exception {
        Offer offer = new ThreeForTwo();
        assertEquals("should be 1.50",new Double(1.50),offer.getTotalPrice(4,0.50));
    }

    @Test(expected = CheckoutException.class)
    public void getTotalPrice4ItemsNullPrice() throws Exception {
        Offer offer = new ThreeForTwo();
        assertEquals("Should throw checkout exception",new Double(1.00),offer.getTotalPrice(4,null));
    }
}