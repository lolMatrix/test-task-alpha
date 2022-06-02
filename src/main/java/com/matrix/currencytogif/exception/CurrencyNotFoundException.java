package com.matrix.currencytogif.exception;

public class CurrencyNotFoundException extends Exception {
    private String currency;

    public CurrencyNotFoundException(String message, String currency) {
        super(message);
        this.currency = currency;
    }

    public String getCurrency() {
        return currency;
    }
}
