package com.rolfie.webdetector.analyse.infra.mock;

import io.webfolder.cdp.session.Session;
import org.mockito.Mockito;

import static org.mockito.Mockito.when;

public class MockComponent {


    public static Session mockGoodSession() {
        Session mock = Mockito.mock(Session.class);

        when(mock.getOuterHtml("body")).thenReturn(
                "<body>\n" +
                        "<img src=\"ImgAnalyzerTest.java\" alt=\"un test\">\n" +
                        "<div>\n" +
                        "<img src=\"hhhe\" alt=\"un autre test \">\n" +
                        "</div>\n" +
                        "</body>\n");

        when(mock.getOuterHtml("head")).thenReturn(
                "<head>\n" +
                        "<meta charset=\"UTF-8\">\n" +
                        "<title>Title</title>\n" +
                        "</head>\n");

        return mock;
    }

    public static Session mockBadSession() {
        Session mock = Mockito.mock(Session.class);

        when(mock.getOuterHtml("body")).thenReturn(
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
                        "</body>\n");

        when(mock.getOuterHtml("head")).thenReturn(
                "<head>\n" +
                        "    <meta charset=\"UTF-8\">\n" +
                        "    <title>Title</title>\n" +
                        "</head>\n");

        return mock;
    }

}