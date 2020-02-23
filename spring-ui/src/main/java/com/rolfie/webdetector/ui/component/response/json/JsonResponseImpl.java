package com.rolfie.webdetector.ui.component.response.json;

import com.rolfie.webdetector.retriever.infra.html.HtmlLine;
import com.rolfie.webdetector.retriever.infra.html.LineNumber;

import java.util.Map;

public abstract class JsonResponseImpl implements JsonResponse {

    //String[row][column]
    protected String[][] data;
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

    protected String getJsonBody() {
        StringBuilder body = new StringBuilder();

        for (String[] column : data) {
            body.append("\"")
                    .append(escapeCharacters(column[0]))
                    .append("\":\"")
                    .append(escapeCharacters(column[1]))
                    .append("\",");
        }
        body.deleteCharAt(body.length() - 1);//remove last comma

        return body.toString();
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
