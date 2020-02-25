package com.rolfie.webdetector.ui.component.response.json;

import com.rolfie.webdetector.retriever.infra.html.HtmlLine;
import com.rolfie.webdetector.retriever.infra.html.LineNumber;
import com.rolfie.webdetector.ui.dto.Line;

import java.util.List;
import java.util.Map;

public class WordResponse extends JsonResponseImpl {

    public WordResponse(Map<LineNumber, HtmlLine> data,
                        String anomalieType) {
        super(data, anomalieType);
    }

    @Override
    public List<Line> getJson() {
        return getJsonBody();
    }
}
