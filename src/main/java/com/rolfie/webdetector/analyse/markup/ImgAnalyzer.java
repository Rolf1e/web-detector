package com.rolfie.webdetector.analyse.markup;

import com.rolfie.webdetector.analyse.infra.pattern.regex.PatternResolver;
import lombok.extern.slf4j.Slf4j;

import java.util.HashMap;
import java.util.Map;

@Slf4j
public class ImgAnalyzer implements TextAnalyzer {

    private static final String pattern = "[\\s.]*<img.*alt=\"\".*>[\\s.]*";
    private static final String imgPattern = "[\\s.]*<img.*>[\\s.]*";

    private final Map<Integer, String> webPage;
    private int countErrors;

    public ImgAnalyzer(Map<Integer, String> webPage) {
        this.webPage = webPage;
        countErrors = 0;
    }

    public int getErrors() {
        return countErrors;
    }

    private Map<Integer, String> getOnlyImg() {
        Map<Integer, String> onlyImg = new HashMap<>();
        PatternResolver resolver = new PatternResolver(imgPattern);

        for (Map.Entry<Integer, String> entry : webPage.entrySet()) {
            final String currentMarkup = entry.getValue();
            if (resolver.resolve(currentMarkup)) {
                onlyImg.put(entry.getKey(), entry.getValue());
            }
        }

        return onlyImg;
    }

    @Override
    public Map<Integer, String> foundError() {
        Map<Integer, String> badElements = new HashMap<>();
        PatternResolver resolver = new PatternResolver(pattern);

        for (Map.Entry<Integer, String> entry : getOnlyImg().entrySet()) {
            final String currentElement = entry.getValue();
            if (resolver.resolve(currentElement)) {
                badElements.put(countErrors++, currentElement);
                log.info("One element is badly coded line :" + entry.getKey());
            }
        }

        return badElements;
    }


}
