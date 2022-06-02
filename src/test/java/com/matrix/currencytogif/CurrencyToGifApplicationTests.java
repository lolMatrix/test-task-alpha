package com.matrix.currencytogif;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.matrix.currencytogif.exception.CurrencyNotFoundException;
import com.matrix.currencytogif.exception.ExternalServiceErrorException;
import com.matrix.currencytogif.exception.GifNotFoundException;
import com.matrix.currencytogif.models.ErrorModel;
import com.matrix.currencytogif.services.ExchangeRateToGifService;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.context.annotation.Import;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;

import java.nio.charset.StandardCharsets;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@Import(ExternalServicesConfiguration.class)
@SpringBootTest
@AutoConfigureMockMvc
class CurrencyToGifApplicationTests {
    @Autowired
    private MockMvc mockMvc;

    @Autowired
    private ExchangeRateToGifService exchangeRateToGifService;

    @Test
    public void contextLoads() {
    }

    @Test
    public void successTest() throws Exception {
        mockMvc.perform(get("/exchange/RUB/gif"))
                .andExpect(status().isOk());
    }

    @Test
    public void currencyNotFoundTest() throws Exception {
        testExceptions(new CurrencyNotFoundException("Код валюты не найден или такой валюты не существует", "CNF"),
                "Код валюты не найден или такой валюты не существует CNF",
                404, "CNF");
    }

    @Test
    public void gifNotFoundTest() throws Exception {
        testExceptions(new GifNotFoundException("Gif изображение по данному курсу не найдено"),
                "Gif изображение по данному курсу не найдено",
                404, "GNF");
    }

    @Test
    public void externalServiceErrorTest() throws Exception {
        testExceptions(new ExternalServiceErrorException("Невозможно получить доступ к сервису", 500),
                "Невозможно получить доступ к сервису",
                500, "ESE");
    }

    @Test
    public void internalErrorTest() throws Exception {
        testExceptions(new NullPointerException("Невозможно получить доступ к сервису"),
                "Произошла непредвиденная ошибка",
                500, "INT");
    }

    private void testExceptions(Throwable throwable, String matchMessage, int code, String currency) throws Exception {
        Mockito.when(exchangeRateToGifService.getGifByCurrencyCode(Mockito.contains(currency)))
                .thenThrow(throwable);

        MvcResult result = mockMvc.perform(get(String.format("/exchange/%s/gif", currency)))
                .andReturn();

        Assertions.assertEquals(code, result.getResponse().getStatus());

        ObjectMapper objectMapper = new ObjectMapper();
        ErrorModel resultEntity = objectMapper.readValue(result.getResponse().getContentAsString(StandardCharsets.UTF_8), ErrorModel.class);
        Assertions.assertEquals(matchMessage, resultEntity.getMessage());
    }

}
