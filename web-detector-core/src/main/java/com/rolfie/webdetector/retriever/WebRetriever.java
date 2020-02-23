package com.rolfie.webdetector.retriever;

import com.rolfie.webdetector.retriever.infra.html.Line;
import com.rolfie.webdetector.retriever.infra.html.LineNumber;

import java.util.Map;

public interface WebRetriever {

    String bodyMarkUp = "body";
    String headMarkUp = "head";

    Map<LineNumber, Line> mappingBody();

    Map<LineNumber, Line> mappingHead();
}
