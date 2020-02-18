package com.rolfie.webdetector.analyse.markup;

import com.rolfie.webdetector.analyse.infra.pattern.regex.PatternResolver;
import lombok.extern.slf4j.Slf4j;

import java.util.HashMap;
import java.util.Map;

@Slf4j
public class ImgAnalyzer implements TextAnalyzer {

    private static final String pattern = "[\\s.]*<img.*alt=\"\".*>[\\s.]*";
    private static final String imgPattern = "[\\s.]*<img.*>[\\s.]*";

    private final Map<String, String> webPage;
    private int countErrors;

    public ImgAnalyzer(Map<String, String> webPage) {
        this.webPage = webPage;
        countErrors = 0;
    }

    public int getErrors() {
        return countErrors;
    }

    private Map<String, String> getOnlyImg() {
        Map<String, String> onlyImg = new HashMap<>();
        PatternResolver resolver = new PatternResolver(imgPattern);

        for (Map.Entry<String, String> entry : webPage.entrySet()) {
            final String currentMarkup = entry.getValue();
            if (resolver.regexResolve(currentMarkup)) {
                onlyImg.put(entry.getKey(), entry.getValue());
            }
        }

        return onlyImg;
    }

    @Override
    public Map<String, String> foundErrors() {
        Map<String, String> badElements = new HashMap<>();
        PatternResolver resolver = new PatternResolver(pattern);

        for (Map.Entry<String, String> entry : getOnlyImg().entrySet()) {
            final String currentElement = entry.getValue();
            if (resolver.regexResolve(currentElement)) {
                badElements.put(String.valueOf(entry.getKey()), currentElement);
                countErrors++;
                log.info("One element is badly coded line :" + entry.getKey());
            }
        }

        return badElements;
    }


}
