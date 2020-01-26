package com.rolfie.webdetector.analyse.infra;

import com.rolfie.webdetector.analyse.markup.ImgAnalyzer;
import com.rolfie.webdetector.analyse.markup.TextAnalyzer;
import com.rolfie.webdetector.retriever.infra.WebPageRetriever;
import org.jsoup.Jsoup;
import org.junit.Assert;
import org.junit.Test;

public class ImgAnalyzerTest {

    @Test
    public void should_get_no_errors() {
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

        WebPageRetriever pageRetriever = new WebPageRetriever(Jsoup.parse(goodHtml));

        TextAnalyzer analyzer = new ImgAnalyzer(pageRetriever.mappingWebSite());
        analyzer.foundError();
        Assert.assertEquals(0, analyzer.getErrors());
    }

    @Test
    public void should_get_errors() {

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


        WebPageRetriever pageRetriever = new WebPageRetriever(Jsoup.parse(badHtml));

        TextAnalyzer analyzer = new ImgAnalyzer(pageRetriever.mappingWebSite());
        analyzer.foundError();
        Assert.assertEquals(2, analyzer.getErrors());
    }

}
