package com.rolfie.webdetector.retriever.infra;

import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.HashMap;
import java.util.Map;

@Component
public class WebPageRetriever {

    private final Document document;
    private final Elements wholeWebSite;

    @Autowired
    public WebPageRetriever(Document document) {
        this.document = document;
        wholeWebSite = retrieveWebSite();
    }

    public Map<Integer, Element> mappingWebSite() {
        Map<Integer, Element> mappedWebSite = new HashMap<>();
        int i = 0;
        for (Element element : retrieveWebSite()) {
            mappedWebSite.put(i++, element);
        }
        return mappedWebSite;
    }

    private Elements retrieveWebSite() {
        return document.getAllElements();
    }

}
