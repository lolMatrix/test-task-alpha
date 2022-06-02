package com.matrix.currencytogif.services;

import com.matrix.currencytogif.exception.CurrencyNotFoundException;
import com.matrix.currencytogif.exception.ExternalServiceErrorException;
import com.matrix.currencytogif.exception.GifNotFoundException;
import com.matrix.currencytogif.models.ExchangeRateToGif;
import org.springframework.stereotype.Service;

@Service
public interface ExchangeRateToGifService {
    ExchangeRateToGif getGifByCurrencyCode(String currencyCode) throws CurrencyNotFoundException, ExternalServiceErrorException, GifNotFoundException;
}
