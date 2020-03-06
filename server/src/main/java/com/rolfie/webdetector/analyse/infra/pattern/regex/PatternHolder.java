package com.rolfie.webdetector.analyse.infra.pattern.regex;

import lombok.Getter;

public enum PatternHolder {

    ACCESSIBLE_PATTERN("[Aa]ccessibilit[eé]"),

    PATTERN_WITH_EMPTY_ALT("[\\s.]*<img.*alt=\"\".*>[\\s.]*"),
    PATTERN_ALT(" alt=\""),
    IMG_PATTERN("[\\s\\w.]*<img.*>[\\s\\w.]*"),

    P_PATTERN("[\\s.]*<img.*>[\\s.]*</p>[\\s.]*"),

    SENTENCE_PATTERN("\\w*[\\.,;].*");

    @Getter
    private String pattern;

    PatternHolder(String pattern) {
        this.pattern = pattern;
    }
}
