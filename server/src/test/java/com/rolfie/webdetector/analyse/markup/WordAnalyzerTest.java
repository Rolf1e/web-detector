package com.rolfie.webdetector.analyse.markup;

import com.rolfie.webdetector.analyse.infra.mock.MockComponent;
import com.rolfie.webdetector.analyse.infra.pattern.regex.PatternHolder;
import com.rolfie.webdetector.retriever.WebFolderRetriever;
import com.rolfie.webdetector.retriever.WebRetriever;
import io.webfolder.cdp.session.Session;
import org.junit.Assert;
import org.junit.Test;

import static com.rolfie.webdetector.analyse.infra.pattern.regex.PatternHolder.ACCESSIBLE_PATTERN;

public class WordAnalyzerTest {

    @Test
    public void should_get_accessibilite_with_accent() {
        Session session = MockComponent.mockSessionWithAccessibiliteWithAccent();
        WebRetriever pageRetriever = new WebFolderRetriever(session);
        TextAnalyzer analyzer = new WordAnalyzer(pageRetriever.mappingBody(), ACCESSIBLE_PATTERN.getPattern());

        analyzer.found();
        Assert.assertEquals(2, analyzer.numberFound());
    }

    @Test
    public void should_get_accessibilite_without_accent() {
        Session session = MockComponent.mockSessionWithAccessibilite();
        WebRetriever pageRetriever = new WebFolderRetriever(session);
        TextAnalyzer analyzer = new WordAnalyzer(pageRetriever.mappingBody(), ACCESSIBLE_PATTERN.getPattern());

        analyzer.found();
        Assert.assertEquals(2, analyzer.numberFound());
    }


}
