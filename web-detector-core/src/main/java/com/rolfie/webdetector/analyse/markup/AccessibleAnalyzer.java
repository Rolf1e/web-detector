package com.rolfie.webdetector.analyse.markup;

import com.rolfie.webdetector.analyse.infra.pattern.regex.PatternResolver;
import com.rolfie.webdetector.retriever.infra.html.HtmlLine;
import com.rolfie.webdetector.retriever.infra.html.Line;
import com.rolfie.webdetector.retriever.infra.html.LineNumber;

import java.util.HashMap;
import java.util.Map;

public class AccessibleAnalyzer implements TextAnalyzer {

    private static String accessiblePattern = "accessibilit[eé]";

    private final Map<LineNumber, Line> webPage;
    private int countErrors;

    public AccessibleAnalyzer(Map<LineNumber, Line> webPage) {
        this.webPage = webPage;
    }

    @Override
    public Map<LineNumber, HtmlLine> found() {
        Map<LineNumber, HtmlLine> accessibleLine = new HashMap<>();
        PatternResolver resolver = new PatternResolver(accessiblePattern);
        webPage.forEach((key, currentMarkup) -> {
            if (resolver.regexResolve(currentMarkup.getValue())) {
                accessibleLine.put(key, Line.create(currentMarkup.getValue()));
            }
        });
        return accessibleLine;
    }

    @Override
    public int numberFound() {
        return 0;
    }
}
