package com.matrix.currencytogif.services.impl;

import com.matrix.currencytogif.configuration.ProjectConfiguration;
import com.matrix.currencytogif.exception.CurrencyNotFoundException;
import com.matrix.currencytogif.exception.ExternalServiceErrorException;
import com.matrix.currencytogif.exception.GifNotFoundException;
import com.matrix.currencytogif.models.ExchangeRateToGif;
import com.matrix.currencytogif.models.dto.ExchangeRate;
import com.matrix.currencytogif.models.dto.Gif;
import com.matrix.currencytogif.services.CurrencyService;
import com.matrix.currencytogif.services.ExchangeRateToGifService;
import com.matrix.currencytogif.services.GifService;
import feign.FeignException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.Random;

@Service
public class ExchangeRateToGifServiceImpl implements ExchangeRateToGifService {
    private GifService gifService;
    private CurrencyService currencyService;
    private ProjectConfiguration projectConfiguration;
    private Random random;

    @Autowired
    public ExchangeRateToGifServiceImpl(GifService gifService, CurrencyService currencyService, ProjectConfiguration projectConfiguration, Random random) {
        this.gifService = gifService;
        this.currencyService = currencyService;
        this.projectConfiguration = projectConfiguration;
        this.random = random;
    }

    @Override
    public ExchangeRateToGif getGifByCurrencyCode(String currencyCode) throws CurrencyNotFoundException, ExternalServiceErrorException, GifNotFoundException {
        LocalDateTime currentTime = LocalDateTime.now();
        int offsetDay = 0;
        if (currentTime.getHour() < 12) {
            offsetDay++;
        }
        ExchangeRate rateNow = getExchangeRateByDate(LocalDate.now().minusDays(offsetDay));
        ExchangeRate rateYesterday = getExchangeRateByDate(LocalDate.now().minusDays(1 + offsetDay));

        if (!(rateNow.getRates().containsKey(currencyCode) || rateYesterday.getRates().containsKey(currencyCode))) {
            throw new CurrencyNotFoundException("Код валюты не найден или такой валюты не существует", currencyCode);
        }

        BigDecimal rateNowValue = rateNow.getRates().get(currencyCode);
        BigDecimal rateYesterdayValue = rateYesterday.getRates().get(currencyCode);

        Gif gifCollection = getRandomGif(rateYesterdayValue.subtract(rateNowValue).compareTo(BigDecimal.ZERO) >= 0 ?
                projectConfiguration.getRateUpSearchGifString() : projectConfiguration.getRateDownSearchGifString());

        if (gifCollection.getData().size() == 0) {
            throw new GifNotFoundException("Gif изображение по данному курсу не найдено");
        }

        String gifUrl = gifCollection.getData().get(random.nextInt(0, gifCollection.getData().size())).getEmbedUrl();

        return ExchangeRateToGif.builder()
                .setBaseCurrency(projectConfiguration.getBaseCurrency())
                .setExchangeRateToday(rateNowValue)
                .setExchangeRateYesterday(rateYesterdayValue)
                .setGifUrl(gifUrl);
    }

    private Gif getRandomGif(String searchString) throws ExternalServiceErrorException {
        try {
            return gifService.getGifList(projectConfiguration.getGifApiKey(), searchString, projectConfiguration.getLimit());
        } catch (FeignException e) {
            throw new ExternalServiceErrorException("Не удалось gif изображение", e.status());
        }
    }

    private ExchangeRate getExchangeRateByDate(LocalDate date) throws ExternalServiceErrorException {
        try {
            return currencyService.getExchangeRateByDate(date.format(DateTimeFormatter.ISO_DATE), projectConfiguration.getCurrencyApiKey(), projectConfiguration.getBaseCurrency());
        } catch (FeignException e) {
            throw new ExternalServiceErrorException("Не удалось получить курс валюты", e.status());
        }
    }
}
