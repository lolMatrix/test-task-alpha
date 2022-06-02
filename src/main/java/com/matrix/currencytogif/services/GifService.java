package com.matrix.currencytogif.services;

import com.matrix.currencytogif.configuration.DecoderConfiguration;
import com.matrix.currencytogif.models.dto.Gif;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

@FeignClient(name = "gifService", url = "${com.matrix.gif.url}", configuration = DecoderConfiguration.class)
public interface GifService {

    @RequestMapping(value = "/gifs/search", method = RequestMethod.GET)
    Gif getGifList(@RequestParam("api_key") String apiKey, @RequestParam("q") String searchString, @RequestParam Integer limit);

}
