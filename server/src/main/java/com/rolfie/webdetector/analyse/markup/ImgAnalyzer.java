package com.rolfie.webdetector.analyse.markup;

import com.rolfie.webdetector.analyse.infra.pattern.regex.PatternResolver;
import com.rolfie.webdetector.retriever.infra.html.HtmlLine;
import com.rolfie.webdetector.retriever.infra.html.Line;
import com.rolfie.webdetector.retriever.infra.html.LineNumber;
import com.rolfie.webdetector.retriever.infra.html.Link;
import lombok.extern.slf4j.Slf4j;

import java.util.HashMap;
import java.util.Map;

import static com.rolfie.webdetector.analyse.infra.pattern.regex.PatternHolder.*;

@Slf4j
public class ImgAnalyzer implements TextAnalyzer {



    private final Map<LineNumber, Line> webPage;
    private int countErrors;

    public ImgAnalyzer(Map<LineNumber, Line> webPage) {
        this.webPage = webPage;
        countErrors = 0;
    }

    public int numberFound() {
        return countErrors;
    }

    @Override
    public Map<LineNumber, HtmlLine> found() {
        Map<LineNumber, HtmlLine> badElements = new HashMap<>();
        PatternResolver resolver = new PatternResolver(PATTERN_WITH_EMPTY_ALT.getPattern());

        for (Map.Entry<LineNumber, Line> entry : getOnlyImg().entrySet()) {

            final Line currentElement = Line.create(entry.getValue().getValue());

            final String value = currentElement.getValue();
            if (resolver.regexResolve(value)
                    || !PatternResolver.seek(value, PATTERN_ALT.getPattern())) {

                final LineNumber key = entry.getKey();
                badElements.put(key, Link.extractLink(value));
                countErrors++;
                if (log.isDebugEnabled()) {
                    log.debug("One element is badly coded line :" + key.getNumber());
                }
            }
        }
        log.info("{} elements have bad alt markup", countErrors);
        return badElements;
    }

    private Map<LineNumber, Line> getOnlyImg() {
        Map<LineNumber, Line> onlyImg = new HashMap<>();
        PatternResolver resolver = new PatternResolver(IMG_PATTERN.getPattern());

        webPage.forEach((key, currentMarkup) -> {
            if (resolver.regexResolve(currentMarkup.getValue())) {
                onlyImg.put(key, Line.create(currentMarkup.getValue()));
            }
        });

        return onlyImg;
    }


}
