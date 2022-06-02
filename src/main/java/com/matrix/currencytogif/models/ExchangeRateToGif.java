package com.matrix.currencytogif.models;

import java.math.BigDecimal;

public class ExchangeRateToGif {
    private String gifUrl;
    private BigDecimal exchangeRateToday;
    private BigDecimal exchangeRateYesterday;
    private String baseCurrency;

    public static ExchangeRateToGif builder() {
        return new ExchangeRateToGif();
    }

    public String getGifUrl() {
        return gifUrl;
    }

    public ExchangeRateToGif setGifUrl(String gifUrl) {
        this.gifUrl = gifUrl;
        return this;
    }

    public BigDecimal getExchangeRateToday() {
        return exchangeRateToday;
    }

    public ExchangeRateToGif setExchangeRateToday(BigDecimal exchangeRateToday) {
        this.exchangeRateToday = exchangeRateToday;
        return this;
    }

    public BigDecimal getExchangeRateYesterday() {
        return exchangeRateYesterday;
    }

    public ExchangeRateToGif setExchangeRateYesterday(BigDecimal exchangeRateYesterday) {
        this.exchangeRateYesterday = exchangeRateYesterday;
        return this;
    }

    public String getBaseCurrency() {
        return baseCurrency;
    }

    public ExchangeRateToGif setBaseCurrency(String baseCurrency) {
        this.baseCurrency = baseCurrency;
        return this;
    }
}
