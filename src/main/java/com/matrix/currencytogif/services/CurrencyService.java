package com.matrix.currencytogif.services;

import com.matrix.currencytogif.configuration.DecoderConfiguration;
import com.matrix.currencytogif.models.dto.ExchangeRate;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

@FeignClient(name = "currencyService", url = "${com.matrix.currency.url}", configuration = DecoderConfiguration.class)
public interface CurrencyService {

    @RequestMapping (value = "/historical/{date}.json", method = RequestMethod.GET)
    ExchangeRate getExchangeRateByDate(@PathVariable String date, @RequestParam("app_id") String apiKey, @RequestParam String base);

}
