package com.rolfie.webdetector.analyse.infra;

import org.jsoup.nodes.Element;

import java.util.Map;

public interface TextAnalyzer {

    Map<Integer, Element> found();

}
