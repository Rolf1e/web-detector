package com.rolfie.webdetector.analyse.markup;

import com.rolfie.webdetector.retriever.infra.html.HtmlLine;
import com.rolfie.webdetector.retriever.infra.html.LineNumber;

import java.util.Map;

public interface TextAnalyzer {

    Map<LineNumber, HtmlLine> foundErrors();
    int getErrors();

}
