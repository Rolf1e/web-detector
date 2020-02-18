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

    public Map<String, String> mappingBody() {
        return mappingContent(retrieveHtmlContent(bodyMarkUp));
    }

    public Map<String, String> mappingHead() {
        return mappingContent(retrieveHtmlContent(headMarkUp));
    }

    private Map<String, String> mappingContent(String toMap) {
        Map<String, String> mappedWebSite = new HashMap<>();
        int i = 0;
        for (String element : toMap.split("(?<=>)")) {
            mappedWebSite.put(String.valueOf(i++), element.toLowerCase());
        }
        return mappedWebSite;
    }


    private String retrieveHtmlContent(String htmlMarkUp) {
        return session.getOuterHtml(htmlMarkUp);
    }

}
