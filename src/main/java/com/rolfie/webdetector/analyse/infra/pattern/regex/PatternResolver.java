package com.rolfie.webdetector.analyse.infra.pattern.regex;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class PatternResolver {

    private final String pattern;

    public PatternResolver(String pattern) {
        this.pattern = pattern;
    }

    public boolean regexResolve(String toResolve) {
        final Pattern compiledPattern = Pattern.compile(pattern);
        Matcher matcher = compiledPattern.matcher(toResolve);
        return matcher.matches();
    }

    public boolean resolve(String toResolve){
        final String[] splitted = toResolve.split(" ");

        for(String str : splitted){
            if(str.matches(pattern)){
                  return true;
            }
        }

        return false;
    }
}
