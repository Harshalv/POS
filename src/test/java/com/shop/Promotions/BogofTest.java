package com.shop.Promotions;

import com.shop.exception.CheckoutException;
import org.junit.Test;

import static org.junit.Assert.*;

/**
 * Test class for Bogof
 */
public class BogofTest {
    @Test
    public void getTotalPrice4Items() throws Exception {
        Offer bogof = new Bogof();
        assertEquals("Should be 1.00",new Double(1.00),bogof.getTotalPrice(4,0.50));
    }

    @Test(expected = CheckoutException.class)
    public void getTotalPrice4ItemsNullPrice() throws Exception {
        Offer bogof = new Bogof();
        assertEquals("Should throw checkout exception",new Double(1.00),bogof.getTotalPrice(4,null));
    }

}