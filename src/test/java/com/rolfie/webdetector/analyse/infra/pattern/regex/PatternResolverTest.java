package com.rolfie.webdetector.analyse.infra.pattern.regex;

import org.junit.Assert;
import org.junit.Test;

public class PatternResolverTest {

    @Test
    public void should_return_true(){
        String toResolve = "    <img src=\"ImgAnalyzerTest.java\" alt=\"un test\">";
        PatternResolver resolver = new PatternResolver("[\\s.]*<img.*>[\\s.]*");
        Assert.assertTrue(resolver.resolve(toResolve));

    }

    @Test
    public void should_return_false(){
        String toResolve = "    <div src=\"ImgAnalyzerTest.java\" alt=\"un test\">";
        PatternResolver resolver = new PatternResolver("[\\s.]*<img.*>[\\s.]*");
        Assert.assertFalse(resolver.resolve(toResolve));

    }

}