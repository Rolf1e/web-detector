package com.rolfie.webdetector.analyse.pattern.regex;

import lombok.Getter;

@Getter
public class PatternResolver {

    private String pattern;
    private String toResolve;

    public PatternResolver(String pattern,
                           String toResolve) {

        this.pattern = pattern;
        this.toResolve = toResolve;
    }




}
