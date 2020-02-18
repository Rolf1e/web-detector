package com.rolfie.webdetector.retriever.infra;

import com.rolfie.webdetector.analyse.infra.mock.MockComponent;
import io.webfolder.cdp.session.Session;
import lombok.extern.slf4j.Slf4j;
import org.junit.Assert;
import org.junit.Test;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

@Slf4j
public class WebPageRetrieverTest {

    private Session session = MockComponent.mockGoodSession();

    @Test
    public void should_parse_body() {
        final String url = "<body>\n" +
                "<img src=\"ImgAnalyzerTest.java\" alt=\"un test\">\n" +
                "<div>\n" +
                "<img src=\"hhhe\" alt=\"un autre test \">\n" +
                "</div>\n" +
                "</body>\n";
        final List<String> actual = new ArrayList<>();

        final List<String> expected = Arrays.stream(url.split("(?<=>)"))
                .map(String::toLowerCase)
                .collect(Collectors.toList());

        WebPageRetriever webPageRetriever = new WebPageRetriever(session);
        final Map<String, String> integerElementMap = webPageRetriever.mappingBody();

        for (Map.Entry<String, String> entry : integerElementMap.entrySet()) {
            actual.add(entry.getValue());
        }

        Assert.assertEquals(actual.size(), expected.size());

    }

    @Test
    public void should_parse_header() {
        final String url = "<head>\n" +
                "<meta charset=\"UTF-8\">\n" +
                "<title>Title</title>\n" +
                "</head>\n";

        final List<String> actual = new ArrayList<>();

        final List<String> expected = Arrays.stream(url.split("(?<=>)"))
                .map(String::toLowerCase)
                .collect(Collectors.toList());

        WebPageRetriever webPageRetriever = new WebPageRetriever(session);
        final Map<String, String> integerElementMap = webPageRetriever.mappingHead();

        for (Map.Entry<String, String> entry : integerElementMap.entrySet()) {
            actual.add(entry.getValue());
        }

        Assert.assertEquals(actual.size(), expected.size());

    }


}
