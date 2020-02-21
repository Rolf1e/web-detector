package com.rolfie.webdetector.retriever.infra;

import com.rolfie.webdetector.retriever.infra.html.Line;
import com.rolfie.webdetector.retriever.infra.html.LineNumber;
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

    public Map<LineNumber, Line> mappingBody() {
        return mappingContent(retrieveHtmlContent(bodyMarkUp));
    }

    public Map<LineNumber, Line> mappingHead() {
        return mappingContent(retrieveHtmlContent(headMarkUp));
    }

    private Map<LineNumber, Line> mappingContent(String toMap) {
        Map<LineNumber, Line> mappedWebSite = new HashMap<>();
        int i = 0;

        for (String element : toMap.split("(?<=>)")) {
            mappedWebSite.put(LineNumber.create(String.valueOf(i++)), Line.create(element.toLowerCase()));
        }

        return mappedWebSite;
    }


    private String retrieveHtmlContent(String htmlMarkUp) {
        return session.getOuterHtml(htmlMarkUp);
    }

}
