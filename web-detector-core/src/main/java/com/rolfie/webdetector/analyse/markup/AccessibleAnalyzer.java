package com.rolfie.webdetector.analyse.markup;

import com.rolfie.webdetector.retriever.infra.html.HtmlLine;
import com.rolfie.webdetector.retriever.infra.html.LineNumber;

import java.util.Map;

public class AccessibleAnalyzer implements TextAnalyzer {

    private static String accessiblePattern = "accessibilit[eé]";


    @Override
    public Map<LineNumber, HtmlLine> found() {
        return null;
    }

    @Override
    public int numberFound() {
        return 0;
    }
}
