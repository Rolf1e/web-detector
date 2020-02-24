package com.rolfie.webdetector.ui.component.response.json;

import com.rolfie.webdetector.retriever.infra.html.HtmlLine;
import com.rolfie.webdetector.retriever.infra.html.LineNumber;
import com.rolfie.webdetector.ui.dto.Line;
import lombok.Getter;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

public abstract class JsonResponseImpl implements JsonResponse {

    //String[row][column]
    protected String[][] data;
    @Getter
    protected String anomalyType;

    protected JsonResponseImpl(String[][] data,
                               String anomalyType) {

        this.data = data;
        this.anomalyType = anomalyType;
    }

    protected JsonResponseImpl(Map<LineNumber, HtmlLine> data,
                               String anomalyType) {

        this.data = getDataFromMap(data);
        this.anomalyType = anomalyType;
    }

    protected List<Line> getJsonBody() {
        List<Line> body = new ArrayList<>();

        for(String[] column : data) {
            body.add(create(column[0], column[1]));
        }

        return body;
    }

    private Line create(String number,
                        String url) {

        return new Line(number, url);
    }

    private String escapeCharacters(String toEscape) {
        StringBuilder escaped = new StringBuilder();

        for (char c : toEscape.toCharArray()) {
            escaped.append(escape(c));
        }

        return escaped.toString();
    }

    private String escape(char c) {
        switch (c) {
            case '"':
                return "";
            default:
                return String.valueOf(c);
        }
    }

    private String[][] getDataFromMap(Map<LineNumber, HtmlLine> oldDataFormat) {
        String[][] data = new String[oldDataFormat.size()][2];

        int indice = 0;
        for (LineNumber element : oldDataFormat.keySet()) {
            data[indice][0] = element.getNumber();
            final HtmlLine link = oldDataFormat
                    .get(element);
            data[indice][1] = link.getValue();
            indice++;
        }

        return data;
    }
}
