package com.rolfie.webdetector.retriever.infra;

import org.jsoup.nodes.Document;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.HashMap;
import java.util.Map;

@Component
public class WebPageRetriever {

    private final Document document;
    private final String wholeWebSite;

    @Autowired
    public WebPageRetriever(Document document) {
        this.document = document;
        this.document.outputSettings().prettyPrint(false);
        wholeWebSite = retrieveWebSite();
    }

    public Map<Integer, String> mappingWebSite() {
        Map<Integer, String> mappedWebSite = new HashMap<>();
        int i = 0;
        for (String element : retrieveWebSite().split("(?<=>)")) {
            mappedWebSite.put(i++, element.toLowerCase());
        }
        return mappedWebSite;
    }

    private String retrieveWebSite() {

        return document.html();
    }

}
