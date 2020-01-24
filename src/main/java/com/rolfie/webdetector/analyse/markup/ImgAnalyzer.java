package com.rolfie.webdetector.analyse.markup;

import com.rolfie.webdetector.analyse.infra.pattern.regex.PatternResolver;
import lombok.extern.slf4j.Slf4j;
import org.jsoup.nodes.Element;

import java.util.HashMap;
import java.util.Map;

@Slf4j
public class ImgAnalyzer implements TextAnalyzer {

    private static final String pattern = "[.]*<img [.]* alt=\"[^\0]*\" [.]*>[.]*";

    private final Map<Integer, String> webPage;
    private int countErrors;

    public ImgAnalyzer(Map<Integer, String> webPage) {
        this.webPage = webPage;
        countErrors = 0;
    }


    @Override
    public Map<Integer, String> foundError() {
        Map<Integer, String> badElements = new HashMap<>();

        for (Map.Entry<Integer, String> entry : webPage.entrySet()) {
            final String currentElement = entry.getValue();
            if (!PatternResolver.resolve(pattern, currentElement)) {
                badElements.put(countErrors++, currentElement);
                log.info("One element is badly coded");
            }
        }

        return badElements;
    }


}
