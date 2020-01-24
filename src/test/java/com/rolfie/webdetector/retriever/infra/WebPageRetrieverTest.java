package com.rolfie.webdetector.retriever.infra;

import lombok.extern.slf4j.Slf4j;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.junit.Assert;
import org.junit.Test;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

@Slf4j
public class WebPageRetrieverTest {


    @Test
    public void should_parse_web_site() {
        final String url = "<!DOCTYPE html>\n" +
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
        final List<String> actual = new ArrayList<>();
        final List<String> expected = Arrays.stream(url.split(">"))
                .map(String::toLowerCase)
                .collect(Collectors.toList());

        Document document = Jsoup.parse(url);
        WebPageRetriever webPageRetriever = new WebPageRetriever(document);
        final Map<Integer, String> integerElementMap = webPageRetriever.mappingWebSite();


        for (Map.Entry<Integer, String> entry : integerElementMap.entrySet()) {
            actual.add(entry.getValue());
        }

        //TODO : fix space difference between the two arrays

        Assert.assertTrue(expected.containsAll(actual)
                && actual.containsAll(expected));

    }

}
