package com.rolfie.webdetector.analyse.infra.pattern.regex;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class PatternResolver {

    public static boolean resolve(String pattern,
                                  String toResolve) {

        final Pattern compiledPattern = Pattern.compile(pattern);
        Matcher matcher = compiledPattern.matcher(toResolve);
        return matcher.matches();
    }

    /**
     * Hidden constructor
     */
    private PatternResolver() {
        throw new IllegalStateException("Utility class");
    }
}
