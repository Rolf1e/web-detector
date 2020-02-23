package com.rolfie.webdetector.ui.component.response.json;

import com.rolfie.webdetector.retriever.infra.html.HtmlLine;
import com.rolfie.webdetector.retriever.infra.html.LineNumber;
import com.rolfie.webdetector.ui.dto.Line;

import java.util.List;
import java.util.Map;

public class AltResponse extends JsonResponseImpl {

    public AltResponse(Map<LineNumber, HtmlLine> data) {
        super(data, "alts");
    }

    @Override
    public List<Line> getJson() {
        return getJsonBody();
    }
}
