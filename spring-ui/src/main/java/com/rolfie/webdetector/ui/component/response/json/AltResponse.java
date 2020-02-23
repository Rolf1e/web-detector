package com.rolfie.webdetector.ui.component.response.json;

import com.rolfie.webdetector.retriever.infra.html.HtmlLine;
import com.rolfie.webdetector.retriever.infra.html.LineNumber;

import java.util.Map;

public class AltResponse extends JsonResponseImpl {

    public AltResponse(Map<LineNumber, HtmlLine> data) {

        super(data, "alts");
    }

    @Override
    public String getJson() {
        return "\"" + anomalyType + "\":{"
                + getJsonBody() + "}";
    }
}
