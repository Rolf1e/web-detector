package com.rolfie.webdetector.ui.component.response.json;

import com.rolfie.webdetector.retriever.infra.UrlHolder;
import com.rolfie.webdetector.retriever.infra.html.HtmlLine;
import com.rolfie.webdetector.retriever.infra.html.LineNumber;
import com.rolfie.webdetector.retriever.infra.html.Link;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

import java.util.LinkedHashMap;
import java.util.Map;

public class JsonResponseTest {

    private UrlHolder urlHolder;

    @Before
    public void init() {
         urlHolder = UrlHolder.getInstance("https://example.com");
    }

    @Test
    public void should_get_json() {
        String expected = "{\"uri\":\"https://example.com\"," +
                "\"anomalie\":{" +
                "\"alts\":{" +
                "\"86\":\"src=http://example.com/test.png\"," +
                "\"84\":\"src=http://example.com/test2.png\"" +
                "}}}";

        Map<LineNumber, HtmlLine> data = new LinkedHashMap<>();
        data.put(LineNumber.create("86"), Link.extractLink("src=\"http://example.com/test.png\""));
        data.put(LineNumber.create("84"), Link.extractLink("src=\"http://example.com/test2.png\""));
        JsonResponse jsonResponse = new JsonResponse(data);
        Assert.assertEquals(expected, jsonResponse.getJson());
    }

}
