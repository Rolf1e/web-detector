package com.rolfie.webdetector.analyse.markup;

import com.rolfie.webdetector.analyse.infra.pattern.regex.PatternResolver;
import com.rolfie.webdetector.retriever.infra.html.HtmlLine;
import com.rolfie.webdetector.retriever.infra.html.Line;
import com.rolfie.webdetector.retriever.infra.html.LineNumber;
import lombok.extern.slf4j.Slf4j;

import java.util.HashMap;
import java.util.Map;

@Slf4j
public class WordAnalyzer implements TextAnalyzer {

    private final Map<LineNumber, Line> webPage;
    private int wordCount;
    private final String word;

    public WordAnalyzer(Map<LineNumber, Line> webPage,
                        String word) {

        this.webPage = webPage;
        this.word = word;
    }

    @Override
    public Map<LineNumber, HtmlLine> found() {
        Map<LineNumber, HtmlLine> accessibleLine = new HashMap<>();

        for (Map.Entry<LineNumber, Line> entry : webPage.entrySet()) {
            LineNumber key = entry.getKey();
            final String context = entry.getValue().getContext();
            if (PatternResolver.seek(context, word)) {
                accessibleLine.put(key, Line.create(context));
                wordCount++;
            }
        }
        log.info("{} elements contain {}", wordCount, word);
        return accessibleLine;
    }

    @Override
    public int numberFound() {
        return wordCount;
    }
}
