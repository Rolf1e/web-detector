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
        final String goodHtml = "<!DOCTYPE html>\n" +
                "<html lang=\"en\">\n" +
                "<head>\n" +
                "    <meta charset=\"UTF-8\">\n" +
                "    <title>Title</title>\n" +
                "</head>\n" +
                "<body>\n" +
                "    <img src=\"ImgAnalyzerTest.java\" alt=\"un test\">\n" +
                "    <div>\n" +
                "        <img src=\"hhhe\" alt=\"un autre test \">\n" +
                "    </div>\n" +
                "</body>\n" +
                "</html>";

        WebPageRetriever pageRetriever = new WebPageRetriever(session);

        TextAnalyzer analyzer = new ImgAnalyzer(pageRetriever.mappingBody());
        analyzer.foundErrors();
        Assert.assertEquals(0, analyzer.getErrors());
    }

    @Test
    public void should_get_errors() {
        Session session = MockComponent.mockBadSession();
        final String badHtml = "<!DOCTYPE html>\n" +
                "<html lang=\"en\">\n" +
                "<head>\n" +
                "    <meta charset=\"UTF-8\">\n" +
                "    <title>Title</title>\n" +
                "</head>\n" +
                "<body>\n" +
                "<img src=\"ImgAnalyzerTest.java\" alt=\"\">\n" +
                "<div>\n" +
                "    <img src=\"hhhe\" alt=\"un autre test \">\n" +
                "</div>\n" +
                "<div>\n" +
                "    <p>\n" +
                "        <img src=\"ImgAnalyzerTest.java\" alt=\"\">\n" +
                "    </p>\n" +
                "</div>\n" +
                "</body>\n" +
                "</html>";

        WebPageRetriever pageRetriever = new WebPageRetriever(session);

        TextAnalyzer analyzer = new ImgAnalyzer(pageRetriever.mappingBody());
        analyzer.foundErrors();
        Assert.assertEquals(2, analyzer.getErrors());
    }

}