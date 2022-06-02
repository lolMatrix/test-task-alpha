package com.matrix.currencytogif.configuration;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.util.Random;

@Configuration
public class ProjectConfiguration {
    @Value("${com.matrix.currency.apiKey}")
    private String currencyApiKey;

    @Value("${com.matrix.gif.apiKey}")
    private String gifApiKey;

    @Value("${com.matrix.currency.base}")
    private String baseCurrency;

    @Value("${com.matrix.gif.rate.up}")
    private String rateUpSearchGifString;
    @Value("${com.matrix.gif.rate.down}")
    private String rateDownSearchGifString;
    @Value("${com.matrix.gif.limit}")
    private Integer limit;

    public String getCurrencyApiKey() {
        return currencyApiKey;
    }

    public void setCurrencyApiKey(String currencyApiKey) {
        this.currencyApiKey = currencyApiKey;
    }

    public String getGifApiKey() {
        return gifApiKey;
    }

    public void setGifApiKey(String gifApiKey) {
        this.gifApiKey = gifApiKey;
    }

    public String getBaseCurrency() {
        return baseCurrency;
    }

    public void setBaseCurrency(String baseCurrency) {
        this.baseCurrency = baseCurrency;
    }

    public String getRateUpSearchGifString() {
        return rateUpSearchGifString;
    }

    public void setRateUpSearchGifString(String rateUpSearchGifString) {
        this.rateUpSearchGifString = rateUpSearchGifString;
    }

    public String getRateDownSearchGifString() {
        return rateDownSearchGifString;
    }

    public void setRateDownSearchGifString(String rateDownSearchGifString) {
        this.rateDownSearchGifString = rateDownSearchGifString;
    }

    public Integer getLimit() {
        return limit;
    }

    public void setLimit(Integer limit) {
        this.limit = limit;
    }

    @Bean
    public Random random() {
        return new Random();
    }
}
