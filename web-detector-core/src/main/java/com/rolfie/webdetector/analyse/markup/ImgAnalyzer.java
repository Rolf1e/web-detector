package com.rolfie.webdetector.analyse.markup;

import com.rolfie.webdetector.analyse.infra.pattern.regex.PatternResolver;
import com.rolfie.webdetector.retriever.infra.html.HtmlLine;
import com.rolfie.webdetector.retriever.infra.html.Line;
import com.rolfie.webdetector.retriever.infra.html.LineNumber;
import com.rolfie.webdetector.retriever.infra.html.Link;
import lombok.extern.slf4j.Slf4j;

import java.util.HashMap;
import java.util.Map;

@Slf4j
public class ImgAnalyzer implements TextAnalyzer {

    private static final String patternWithEmptyAlt = "[\\s.]*<img.*alt=\"\".*>[\\s.]*";
    private static final String patternAlt = " alt=\"";

    private static final String imgPattern = "[\\s.]*<img.*>[\\s.]*";

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
        PatternResolver resolver = new PatternResolver(patternWithEmptyAlt);

        for (Map.Entry<LineNumber, Line> entry : getOnlyImg().entrySet()) {

            final Line currentElement = Line.create(entry.getValue().getValue());

            final String value = currentElement.getValue();
            if (resolver.regexResolve(value)
                    || PatternResolver.seek(value, patternAlt)) {

                final LineNumber key = entry.getKey();
                badElements.put(key, Link.extractLink(value));
                countErrors++;
                log.info("One element is badly coded line :" + key.getNumber());
            }
        }

        return badElements;
    }

    private Map<LineNumber, Line> getOnlyImg() {
        Map<LineNumber, Line> onlyImg = new HashMap<>();
        PatternResolver resolver = new PatternResolver(imgPattern);

        for (Map.Entry<LineNumber, Line> entry : webPage.entrySet()) {

            final Line currentMarkup = entry.getValue();

            if (resolver.regexResolve(currentMarkup.getValue())) {
                onlyImg.put(entry.getKey(), Line.create(entry.getValue().getValue()));
            }
        }

        return onlyImg;
    }


}
