package com.rolfie.webdetector.analyse.infra;

import com.rolfie.webdetector.analyse.infra.mock.MockComponent;
import com.rolfie.webdetector.analyse.markup.ImgAnalyzer;
import com.rolfie.webdetector.analyse.markup.TextAnalyzer;
import com.rolfie.webdetector.retriever.WebFolderRetriever;
import com.rolfie.webdetector.retriever.WebRetriever;
import io.webfolder.cdp.session.Session;
import org.junit.Assert;
import org.junit.Test;

public class ImgAnalyzerTest {


    @Test
    public void should_get_no_errors() {
        Session session = MockComponent.mockGoodSession();

        WebRetriever pageRetriever = WebFolderRetriever.getInstance(session);

        TextAnalyzer analyzer = new ImgAnalyzer(pageRetriever.mappingBody());
        analyzer.found();
        Assert.assertEquals(0, analyzer.numberFound());
    }

    @Test
    public void should_get_errors() {
        Session session = MockComponent.mockBadSession();

        WebRetriever pageRetriever = WebFolderRetriever.getInstance(session);

        TextAnalyzer analyzer = new ImgAnalyzer(pageRetriever.mappingBody());
        analyzer.found();
        Assert.assertEquals(2, analyzer.numberFound());
    }

}
