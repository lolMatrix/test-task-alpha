package com.matrix.currencytogif;

import com.matrix.currencytogif.configuration.ProjectConfiguration;
import com.matrix.currencytogif.services.CurrencyService;
import com.matrix.currencytogif.services.ExchangeRateToGifService;
import com.matrix.currencytogif.services.GifService;
import com.matrix.currencytogif.services.impl.ExchangeRateToGifServiceImpl;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.TestConfiguration;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.context.annotation.Bean;

@TestConfiguration
public class ExternalServicesConfiguration {
    @Bean
    public ExchangeRateToGifService exchangeRateToGifService() {
        return Mockito.mock(ExchangeRateToGifServiceImpl.class);
    }
}
