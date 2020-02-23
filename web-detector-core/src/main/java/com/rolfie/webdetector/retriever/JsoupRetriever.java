package com.rolfie.webdetector.retriever;

import com.rolfie.webdetector.retriever.infra.html.Line;
import com.rolfie.webdetector.retriever.infra.html.LineNumber;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.nodes.Node;
import org.jsoup.select.Elements;

import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

public class JsoupRetriever implements WebRetriever {

    private static WebRetriever instance = null;

    private Document document;

    public static WebRetriever getInstance(Document document) {
        if (instance == null) {
            instance = new JsoupRetriever(document);
        }
        return instance;
    }

    private JsoupRetriever(Document document) {
        this.document = document;
    }

    @Override
    public Map<LineNumber, Line> mappingBody() {
        return mappingContent(document.body());
    }

    @Override
    public Map<LineNumber, Line> mappingHead() {
        return mappingContent(document.head());
    }

    private Map<LineNumber, Line> mappingContent(Element toMap) {
        Map<LineNumber, Line> mappedContent = new HashMap<>();
        int i = 0;
        //TODO : parsing through nodes to get line by line

        for (Node child : toMap.childNodes()) {
            mappedContent.put(LineNumber.create(String.valueOf(i++)), Line.create(child.outerHtml()));
        }

        return mappedContent;
    }
}
