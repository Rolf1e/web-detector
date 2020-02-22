package com.rolfie.webdetector.analyse.infra.pattern.regex;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class PatternResolver {

    private final String pattern;
    private Boolean caseSensitive;

    public PatternResolver(String pattern) {
        this.pattern = pattern;
        this.caseSensitive = true;
    }

    public PatternResolver(String pattern,
                           Boolean caseSensitive) {
        this.pattern = pattern;
        this.caseSensitive = caseSensitive;
    }

    public boolean regexResolve(String toResolve) {
        Pattern compiledPattern;
        compiledPattern = getCaseSetting();
        Matcher matcher = compiledPattern.matcher(toResolve);
        return matcher.matches();
    }

    public String extractContent(String toResolve) throws RegexException {
        final Pattern compliedPattern = getCaseSetting();
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

    private Pattern getCaseSetting() {
        if (caseSensitive) {
            return Pattern.compile(pattern);
        }
        return Pattern.compile(pattern, Pattern.CASE_INSENSITIVE);
    }

    public class RegexException extends Exception {


        public RegexException() {
            super();
        }
    }
}
