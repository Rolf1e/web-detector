package com.rolfie.webdetector.mappings;

import lombok.Getter;

@Getter
public class UrlHolder {

    private static UrlHolder instance = null;

    private final String url;

    private UrlHolder(String url) {
        this.url = url;
    }

    static UrlHolder getInstance(String url) {
         instance = new UrlHolder(url);
         return instance;
    }

    public static UrlHolder getInstance() {
        return instance;
    }
}
