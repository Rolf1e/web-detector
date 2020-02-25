package com.rolfie.webdetector.ui.mappings;

import lombok.Getter;

@Getter
public class UrlHolder {

    public static UrlHolder instance = null;

    private final String url;

    private UrlHolder(String url) {
        this.url = url;
    }

    static UrlHolder getInstance(String url) {
        return instance = new UrlHolder(url);
    }

    public static UrlHolder getInstance() {
        return instance;
    }
}
