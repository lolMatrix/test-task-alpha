package com.matrix.currencytogif.models.dto;

import java.util.List;


public class Gif {
    private List<GifData> data;

    public List<GifData> getData() {
        return data;
    }

    public void setData(List<GifData> data) {
        this.data = data;
    }
}
