package com.rolfie.webdetector.retriever;

import com.rolfie.webdetector.retriever.infra.html.Line;
import com.rolfie.webdetector.retriever.infra.html.LineNumber;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.nodes.Node;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class JsoupRetriever implements WebRetriever {

    private static WebRetriever instance = null;

    private Document document;
    private Integer lineCount;

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
        lineCount = 0;
        for (Node node : toMap.childNodes()) {
            mappedContent.putAll(getChilds(node));
        }
        return mappedContent;
    }

    private Map<LineNumber, Line> getChilds(Node father) {
        Map<LineNumber, Line> childs = new HashMap<>();
        if(father.childNodes().isEmpty()){
            childs.put(LineNumber.create(String.valueOf(lineCount++)), Line.create(father.outerHtml()));
            return childs;
        }
        childs.putAll(getSubChilds(father));
        return childs;
    }

    private Map<LineNumber, Line> getSubChilds(Node father) {
        Map<LineNumber, Line> childs = new HashMap<>();
        for (Node child : father.childNodes()) {
            final List<Node> subChildNode = child.childNodes();
            if (subChildNode.isEmpty()) {
                childs.put(LineNumber.create(String.valueOf(lineCount++)), Line.create(child.outerHtml()));
            }
            childs.putAll(getChilds(child));
        }
        return childs;
    }

}
