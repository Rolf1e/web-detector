package com.rolfie.webdetector.analyse.markup;

import com.rolfie.webdetector.analyse.infra.pattern.regex.PatternResolver;
import lombok.extern.slf4j.Slf4j;

import java.util.HashMap;
import java.util.Map;

@Slf4j
public class ImgAnalyzer implements TextAnalyzer {

    private static final String pattern = ".*<img.*alt=\"\".*>.*";
    private static final String imgPattern = ".*<img.*>.";

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

        for (Map.Entry<Integer, String> entry : webPage.entrySet()) {
            final String currentMarkup = entry.getValue();
            if(PatternResolver.resolve(imgPattern, currentMarkup)){
                onlyImg.put(entry.getKey(), entry.getValue());
            }
        }

        return onlyImg;
    }

    @Override
    public Map<Integer, String> foundError() {
        Map<Integer, String> badElements = new HashMap<>();

        for (Map.Entry<Integer, String> entry : getOnlyImg().entrySet()) {
            final String currentElement = entry.getValue();
            if (PatternResolver.resolve(pattern, currentElement)) {
                badElements.put(countErrors++, currentElement);
                log.info("One element is badly coded");
            }
        }

        return badElements;
    }


}
