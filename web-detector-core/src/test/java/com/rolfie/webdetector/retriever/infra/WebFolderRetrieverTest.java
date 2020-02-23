package com.rolfie.webdetector.retriever.infra;

import com.rolfie.webdetector.analyse.infra.mock.MockComponent;
import com.rolfie.webdetector.retriever.WebFolderRetriever;
import com.rolfie.webdetector.retriever.WebRetriever;
import com.rolfie.webdetector.retriever.infra.html.Line;
import com.rolfie.webdetector.retriever.infra.html.LineNumber;
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
public class WebFolderRetrieverTest {

    private Session session = MockComponent.mockGoodSession();

    @Test
    public void should_parse_body() {
        final String url = "<body>\n" +
                "<img src=\"ImgAnalyzerTest.java\" alt=\"un test\">\n" +
                "<div>\n" +
                "<img src=\"hhhe\" alt=\"un autre test \">\n" +
                "</div>\n" +
                "</body>\n";
        final List<Line> actual = new ArrayList<>();

        final List<Line> expected = Arrays.stream(url.split("(?<=>)"))
                .map(Line::create)
                .collect(Collectors.toList());

        WebRetriever webFolderRetriever = WebFolderRetriever.getInstance(session);
        final Map<LineNumber, Line> integerElementMap = webFolderRetriever.mappingBody();

        for (Map.Entry<LineNumber, Line> entry : integerElementMap.entrySet()) {
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

        final List<Line> actual = new ArrayList<>();

        final List<Line> expected = Arrays.stream(url.split("(?<=>)"))
                .map(Line::create)
                .collect(Collectors.toList());

        WebRetriever webFolderRetriever = WebFolderRetriever.getInstance(session);
        final Map<LineNumber, Line> integerElementMap = webFolderRetriever.mappingHead();

        for (Map.Entry<LineNumber, Line> entry : integerElementMap.entrySet()) {
            actual.add(entry.getValue());
        }

        Assert.assertEquals(actual.size(), expected.size());

    }


}
