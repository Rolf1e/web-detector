package com.rolfie.webdetector.retriever;

import com.rolfie.webdetector.retriever.infra.html.Line;
import com.rolfie.webdetector.retriever.infra.html.LineNumber;
import io.webfolder.cdp.session.Session;

import java.util.HashMap;
import java.util.Map;

import static com.rolfie.webdetector.retriever.WebRetriever.MarkUp.BODY_MARK_UP;
import static com.rolfie.webdetector.retriever.WebRetriever.MarkUp.HEAD_MARK_UP;

public class WebFolderRetriever implements WebRetriever {

    private final Session session;

    public WebFolderRetriever(Session session) {
        this.session = session;
    }

    @Override
    public Map<LineNumber, Line> mappingBody() {
        return mappingContent(retrieveHtmlContent(BODY_MARK_UP));
    }

    @Override
    public Map<LineNumber, Line> mappingHead() {
        return mappingContent(retrieveHtmlContent(HEAD_MARK_UP));
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
