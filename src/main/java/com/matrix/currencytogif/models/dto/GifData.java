package com.matrix.currencytogif.models.dto;

import com.fasterxml.jackson.annotation.JsonProperty;

public class GifData {

    @JsonProperty("embed_url")
    private String embedUrl;

    public String getEmbedUrl() {
        return embedUrl;
    }

    public void setEmbedUrl(String embedUrl) {
        this.embedUrl = embedUrl;
    }
}
