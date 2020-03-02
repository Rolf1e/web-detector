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
    private String word;

    public WordAnalyzer(Map<LineNumber, Line> webPage,
                        String word) {

        this.webPage = webPage;
        this.word = word;
    }

    @Override
    public Map<LineNumber, HtmlLine> found() {
        Map<LineNumber, HtmlLine> accessibleLine = new HashMap<>();

        webPage.forEach((key, currentMarkup) -> {
            if (PatternResolver.seek(currentMarkup.getValue(), word)) {
                accessibleLine.put(key, Line.create(currentMarkup.getValue()));
                wordCount++;
            }
        });
        log.info("{} elements contain {}", wordCount, word);
        return accessibleLine;
    }

    @Override
    public int numberFound() {
        return wordCount;
    }
}
