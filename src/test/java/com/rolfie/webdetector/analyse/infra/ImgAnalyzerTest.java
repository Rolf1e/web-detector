package com.rolfie.webdetector.analyse.infra;

import com.rolfie.webdetector.analyse.markup.ImgAnalyzer;
import org.jsoup.nodes.Attributes;
import org.jsoup.nodes.Element;
import org.jsoup.parser.Tag;
import org.junit.Before;
import org.junit.Test;

public class ImgAnalyzerTest {

    private ImgAnalyzer analyzer;

    @Before
    public void init() {
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


    }


    @Test
    public void should_get_no_errors() {

    }

    @Test
    public void should_get_errors() {

    }

}
