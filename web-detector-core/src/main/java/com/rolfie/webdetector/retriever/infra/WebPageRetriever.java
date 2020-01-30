package com.rolfie.webdetector.retriever.infra;

import io.webfolder.cdp.session.Session;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.HashMap;
import java.util.Map;

@Component
public class WebPageRetriever {

    private static final String bodyMarkUp = "body";
    private static final String headMarkUp = "head";

    private final String body;
    private final String head;
    private final Session session;

    @Autowired
    public WebPageRetriever(Session session) {
        this.session = session;
        body = retrieveHtmlContent(bodyMarkUp);
        head = retrieveHtmlContent(headMarkUp);
    }

    public Map<Integer, String> mappingBody() {
        return mappingContent(body);
    }

    public Map<Integer, String> mappingHead() {
        return mappingContent(head);
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
