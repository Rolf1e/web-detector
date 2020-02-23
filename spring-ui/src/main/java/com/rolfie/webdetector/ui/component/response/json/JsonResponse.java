package com.rolfie.webdetector.ui.component.response.json;

import com.rolfie.webdetector.retriever.infra.UrlHolder;
import com.rolfie.webdetector.retriever.infra.html.HtmlLine;
import com.rolfie.webdetector.retriever.infra.html.LineNumber;

import java.util.Map;

public class JsonResponse {

    //String[row][column]
    private String[][] data;

    public JsonResponse(String[][] data) {

        this.data = data;
    }

    public JsonResponse(Map<LineNumber, HtmlLine> data) {
        this.data = getDataFromMap(data);
    }

    public String getJson() {
        return "{"
                + "\"uri\":\"" + getUri() + "\","
                + getJsonBody()
                + "}";
    }

    private String getUri() {
        return UrlHolder.getInstance().getUrl();
    }


    public String getJsonBody() {
        StringBuilder body = new StringBuilder("\"anomalie\":{");
        body.append("\"alts\":{");
        for (String[] column : data) {
            body.append("\"")
                    .append(column[0])
                    .append("\":\"")
                    .append(escapeCharacters(column[1]))
                    .append("\",");
        }
        body.deleteCharAt(body.length()-1);
        return body.append("}}").toString();
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
