package com.rolfie.webdetector.analyse.markup;

import com.rolfie.webdetector.retriever.infra.html.HtmlLine;
import com.rolfie.webdetector.retriever.infra.html.LineNumber;

import java.util.Map;

public class FontFamilyAnalyzer implements TextAnalyzer {


    @Override
    public Map<LineNumber, HtmlLine> foundErrors() {
        return null;
    }

    @Override
    public int getErrors() {
        return 0;
    }
}
