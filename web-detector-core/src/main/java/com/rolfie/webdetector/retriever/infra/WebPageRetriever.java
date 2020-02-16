package com.rolfie.webdetector.retriever.infra;

import io.webfolder.cdp.session.Session;

import java.util.HashMap;
import java.util.Map;

public class WebPageRetriever {

    private static final String bodyMarkUp = "body";
    private static final String headMarkUp = "head";

    private final Session session;

    public WebPageRetriever(Session session) {
        this.session = session;
    }

    public Map<Integer, String> mappingBody() {
        return mappingContent(retrieveHtmlContent(bodyMarkUp));
    }

    public Map<Integer, String> mappingHead() {
        return mappingContent(retrieveHtmlContent(headMarkUp));
    }

    private Map<Integer, String> mappingContent(String toMap) {
        Map<Integer, String> mappedWebSite = new HashMap<>();
        int i = 0;
        for (String element : toMap.split("(?<=>)")) {
            mappedWebSite.put(i++, element.toLowerCase());
        }
        return mappedWebSite;
    }


    private String retrieveHtmlContent(String htmlMarkUp) {
        return session.getOuterHtml(htmlMarkUp);
    }

}
