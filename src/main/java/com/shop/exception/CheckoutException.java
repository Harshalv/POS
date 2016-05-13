package com.shop.exception;

/**
 * Exception to be used for checkout specific errors
 */
public class CheckoutException extends Exception {
    String errorMessage;

    public CheckoutException(String error) {
        this.errorMessage = error;
    }

    @Override
    public String toString() {
        return "CheckoutException{" +
                "errorMessage='" + errorMessage + '\'' +
                '}';
    }

}
