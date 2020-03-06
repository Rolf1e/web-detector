package com.rolfie.webdetector.analyse.markup;

import com.rolfie.webdetector.analyse.infra.mock.MockComponent;
import com.rolfie.webdetector.retriever.WebFolderRetriever;
import com.rolfie.webdetector.retriever.WebRetriever;
import io.webfolder.cdp.session.Session;
import org.junit.Assert;
import org.junit.Test;

public class TextLengthAnalyzerTest {

    @Test
    public void should_get_errors() {
        Session session = MockComponent.mockBadSessionForTextLength();
        WebRetriever retriever = new WebFolderRetriever(session);
        TextAnalyzer analyzer = new TextLengthAnalyzer(retriever.mappingBody());
        analyzer.found();

        Assert.assertEquals(2, analyzer.numberFound());
    }

    @Test
    public void should_not_get_errors() {
        Session session = MockComponent.mockGoodSessionForTextLength();
        WebRetriever retriever = new WebFolderRetriever(session);
        TextAnalyzer analyzer = new TextLengthAnalyzer(retriever.mappingBody());
        analyzer.found();

        Assert.assertEquals(0, analyzer.numberFound());
    }

}