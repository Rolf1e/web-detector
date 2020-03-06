package com.rolfie.webdetector.analyse.markup;

import com.rolfie.webdetector.analyse.infra.pattern.regex.PatternResolver;
import com.rolfie.webdetector.retriever.infra.html.HtmlLine;
import com.rolfie.webdetector.retriever.infra.html.Line;
import com.rolfie.webdetector.retriever.infra.html.LineNumber;
import lombok.extern.slf4j.Slf4j;

import java.util.HashMap;
import java.util.Map;

import static com.rolfie.webdetector.analyse.infra.pattern.regex.PatternHolder.SENTENCE_PATTERN;

@Slf4j
public class TextLengthAnalyzer implements TextAnalyzer {

    private final Map<LineNumber, Line> webpage;
    private int tooLongSentence;

    public TextLengthAnalyzer(Map<LineNumber, Line> webpage) {

        this.webpage = webpage;
        tooLongSentence = 0;
    }

    @Override
    public Map<LineNumber, HtmlLine> found() {
        Map<LineNumber, HtmlLine> tooLong = new HashMap<>();

        for (Map.Entry<LineNumber, Line> entry : getText().entrySet()) {
            LineNumber key = entry.getKey();
            final String sentence = entry.getValue().getContext();
            if (getSentenceLength(sentence) > 20) {
                tooLong.put(key, Line.create(sentence));
                tooLongSentence++;
                log.debug("One sentence is too long line : {}", key.getNumber());
            }
        }
        log.info("{} sentences are too long", tooLongSentence);
        return tooLong;
    }

    private Map<LineNumber, Line> getText() {
        Map<LineNumber, Line> texts = new HashMap<>();

        for (Map.Entry<LineNumber, Line> entry : webpage.entrySet()) {
            LineNumber key = entry.getKey();
            final String context = entry.getValue().getContext();
            if (PatternResolver.seek(context, SENTENCE_PATTERN.getPattern())) { //TODO : fix regex
                texts.put(key, Line.create(context));
            }
        }

        return texts;
    }

    private int getSentenceLength(String sentence) {
        return sentence.split(" ").length;
    }

    @Override
    public int numberFound() {
        return tooLongSentence;
    }
}
