package com.matrix.currencytogif;

import com.matrix.currencytogif.configuration.ProjectConfiguration;
import com.matrix.currencytogif.exception.CurrencyNotFoundException;
import com.matrix.currencytogif.exception.ExternalServiceErrorException;
import com.matrix.currencytogif.exception.GifNotFoundException;
import com.matrix.currencytogif.models.dto.ExchangeRate;
import com.matrix.currencytogif.models.dto.Gif;
import com.matrix.currencytogif.models.dto.GifData;
import com.matrix.currencytogif.services.CurrencyService;
import com.matrix.currencytogif.services.ExchangeRateToGifService;
import com.matrix.currencytogif.services.GifService;
import com.matrix.currencytogif.services.impl.ExchangeRateToGifServiceImpl;
import feign.FeignException;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;

import java.math.BigDecimal;
import java.util.List;
import java.util.Map;

@SpringBootTest
public class ExchangeRateToGifServiceTest {
    private ExchangeRateToGifService service;
    @MockBean
    private GifService gifService;
    @MockBean
    private CurrencyService currencyService;

    @BeforeEach
    public void init(@Autowired ProjectConfiguration projectConfiguration) {
        service = new ExchangeRateToGifServiceImpl(gifService, currencyService, projectConfiguration, projectConfiguration.random());
    }

    @Test
    public void successTest() throws Exception {
        Gif mockGif = Mockito.mock(Gif.class);
        Mockito.when(mockGif.getData())
                .thenReturn(List.of(Mockito.mock(GifData.class)));
        ExchangeRate mockRate = getMockedExchangeRate();

        Mockito.when(gifService.getGifList(Mockito.any(), Mockito.any(), Mockito.any()))
                .thenReturn(mockGif);

        Mockito.when(currencyService.getExchangeRateByDate(Mockito.any(), Mockito.any(), Mockito.any()))
                .thenReturn(mockRate)
                .thenReturn(mockRate);

        service.getGifByCurrencyCode("RUB");
    }

    @Test
    public void serviceErrorTest() {
        ExchangeRate exchangeRate = getMockedExchangeRate();
        Mockito.when(gifService.getGifList(Mockito.any(), Mockito.any(), Mockito.any()))
                .thenThrow(FeignException.class);

        Mockito.when(currencyService.getExchangeRateByDate(Mockito.any(), Mockito.any(), Mockito.any()))
                .thenReturn(exchangeRate)
                .thenReturn(exchangeRate)
                .thenThrow(FeignException.class);

        Assertions.assertThrows(ExternalServiceErrorException.class, () -> service.getGifByCurrencyCode("RUB"));
        Assertions.assertThrows(ExternalServiceErrorException.class, () -> service.getGifByCurrencyCode(""));
    }

    @Test
    public void currencyNotFoundTest() {
        Mockito.when(currencyService.getExchangeRateByDate(Mockito.any(), Mockito.any(), Mockito.any()))
                .thenReturn(Mockito.mock(ExchangeRate.class));
        Assertions.assertThrows(CurrencyNotFoundException.class, () -> service.getGifByCurrencyCode("RUB"));
    }

    @Test
    public void gifNotFoundTest() {
        ExchangeRate mockedRate = getMockedExchangeRate();
        Mockito.when(currencyService.getExchangeRateByDate(Mockito.any(), Mockito.any(), Mockito.any()))
                .thenReturn(mockedRate)
                .thenReturn(mockedRate);
        Mockito.when(gifService.getGifList(Mockito.any(), Mockito.any(), Mockito.any()))
                .thenReturn(Mockito.mock(Gif.class));
        Assertions.assertThrows(GifNotFoundException.class, () -> service.getGifByCurrencyCode("RUB"));
    }

    private ExchangeRate getMockedExchangeRate() {
        var mockRate = Mockito.mock(ExchangeRate.class);
        Mockito.when(mockRate.getRates())
                .thenReturn(Map.of("RUB", BigDecimal.ONE));
        return mockRate;
    }

}
