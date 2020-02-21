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

    public String extractContent(String toResolve) throws RegexException {
        final Pattern compliedPattern = Pattern.compile(pattern);
        Matcher matcher = compliedPattern.matcher(toResolve);
        if (matcher.find()) {
            return matcher.group(0);
        }
        throw new RegexException();
    }

    public boolean resolve(String toResolve) {
        final String[] splitted = toResolve.split(" ");

        for (String str : splitted) {
            if (str.matches(pattern)) {
                return true;
            }
        }

        return false;
    }

    public static boolean seek(String toResolve,
                               String pattern) {

        final Pattern compiledPattern = Pattern.compile(pattern);
        return compiledPattern.matcher(toResolve)
                .find();
    }

    public class RegexException extends Exception {

        public RegexException() {
            super();
        }
    }
}
