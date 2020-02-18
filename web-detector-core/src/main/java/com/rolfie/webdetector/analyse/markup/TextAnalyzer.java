package com.rolfie.webdetector.analyse.markup;

import java.util.Map;

public interface TextAnalyzer {

    Map<String, String> foundErrors();
    int getErrors();

}
