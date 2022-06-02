package com.matrix.currencytogif.controllers;

import com.matrix.currencytogif.models.ExchangeRateToGif;
import com.matrix.currencytogif.services.ExchangeRateToGifService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/exchange")
public class CurrencyToGifController {
    private ExchangeRateToGifService exchangeRateToGifService;

    @Autowired
    public CurrencyToGifController(ExchangeRateToGifService exchangeRateToGifService) {
        this.exchangeRateToGifService = exchangeRateToGifService;
    }

    @RequestMapping(value = "/{currencyCode}/gif", method = RequestMethod.GET)
    public ExchangeRateToGif getGifByCurrency(@PathVariable String currencyCode) throws Exception{
        return exchangeRateToGifService.getGifByCurrencyCode(currencyCode);
    }

}
