package com.rolfie.webdetector.analyse.markup;

import java.util.Map;

public interface TextAnalyzer {

    Map<Integer, String> foundErrors();
    int getErrors();

}
