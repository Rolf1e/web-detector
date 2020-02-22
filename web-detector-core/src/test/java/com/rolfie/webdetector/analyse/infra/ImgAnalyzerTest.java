package com.rolfie.webdetector.analyse.infra;

import com.rolfie.webdetector.analyse.infra.mock.MockComponent;
import com.rolfie.webdetector.analyse.markup.ImgAnalyzer;
import com.rolfie.webdetector.analyse.markup.TextAnalyzer;
import com.rolfie.webdetector.retriever.infra.WebPageRetriever;
import io.webfolder.cdp.session.Session;
import org.junit.Assert;
import org.junit.Test;

public class ImgAnalyzerTest {


    @Test
    public void should_get_no_errors() {
        Session session = MockComponent.mockGoodSession();

        WebPageRetriever pageRetriever = new WebPageRetriever(session);

        TextAnalyzer analyzer = new ImgAnalyzer(pageRetriever.mappingBody());
        analyzer.foundErrors();
        Assert.assertEquals(0, analyzer.getErrors());
    }

    @Test
    public void should_get_errors() {
        Session session = MockComponent.mockBadSession();

        WebPageRetriever pageRetriever = new WebPageRetriever(session);

        TextAnalyzer analyzer = new ImgAnalyzer(pageRetriever.mappingBody());
        analyzer.foundErrors();
        Assert.assertEquals(3, analyzer.getErrors());
    }

}
