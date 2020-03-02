package com.rolfie.webdetector.component.response.json;

import com.rolfie.webdetector.retriever.infra.html.HtmlLine;
import com.rolfie.webdetector.retriever.infra.html.Line;
import com.rolfie.webdetector.retriever.infra.html.LineNumber;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class AltResponseTest {

    private JsonResponse response;

    @Before
    public void init() {
        Map<LineNumber, HtmlLine> data = new HashMap<>();
        data.put(LineNumber.create("1"), Line.create("test"));
        data.put(LineNumber.create("2"), Line.create("test2"));
        response = new AltResponse(data);
    }

    @Test
    public void should_get_json() {
        final List<com.rolfie.webdetector.mappings.dto.Line> expected = Arrays.asList(
                new com.rolfie.webdetector.mappings.dto.Line("1", "test"),
                new com.rolfie.webdetector.mappings.dto.Line("2", "test2"));
        final List<com.rolfie.webdetector.mappings.dto.Line> actual = response.getJson();

        Assert.assertTrue(expected.containsAll(actual)
                && actual.containsAll(expected));
    }
}
