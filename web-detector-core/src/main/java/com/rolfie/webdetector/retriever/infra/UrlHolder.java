package com.rolfie.webdetector.retriever.infra;

import lombok.Getter;

@Getter
public class UrlHolder {

    public static UrlHolder instance = null;

    private final String url;

    private UrlHolder(String url) {
        this.url = url;
    }

    public static UrlHolder getInstance(String url) {
        if (instance == null) {
            instance = new UrlHolder(url);
        }
        return instance;
    }

    public static UrlHolder getInstance() {
        if (instance == null) {
            throw new IllegalStateException("Not already instancied");
        }
        return instance;
    }
}
