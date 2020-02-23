package com.rolfie.webdetector.retriever;

import com.rolfie.webdetector.retriever.infra.html.Line;
import com.rolfie.webdetector.retriever.infra.html.LineNumber;
import io.webfolder.cdp.session.Session;

import java.util.HashMap;
import java.util.Map;

public class WebFolderRetriever implements WebRetriever {

    private static WebRetriever instance = null;

    private final Session session;

    public static WebRetriever getInstance(Session session) {
        if (instance == null) {
            instance = new WebFolderRetriever(session);
        }
        return instance;
    }

    private WebFolderRetriever(Session session) {
        this.session = session;
    }

    @Override
    public Map<LineNumber, Line> mappingBody() {
        return mappingContent(retrieveHtmlContent(bodyMarkUp));
    }

    @Override
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
