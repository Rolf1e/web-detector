package com.rolfie.webdetector.analyse.infra;

import org.jsoup.nodes.Element;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Component;

import java.util.Map;

@Component
public class LinkAnalyzer implements TextAnalyzer {

    private static final String pattern = "<a";

    private final Map<Integer, Element> webPage;

    public LinkAnalyzer(@Qualifier("onePageMappedRetriever") Map<Integer, Element> webPage) {
        this.webPage = webPage;
    }


    @Override
    public Map<Integer, Element> found() {


        return null;
    }

}
