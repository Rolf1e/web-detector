package com.rolfie.webdetector.ui.component.html;

import java.util.List;
import java.util.Map;

public class HtmlTable {

    private List<String> headers;
    //String[row][column]
    private String[][] data;

    public HtmlTable(List<String> headers,
                     String[][] data) {

        this.headers = headers;
        this.data = data;
    }

    public HtmlTable(List<String> headers,
                     Map<String, String> data) {

        this.headers = headers;
        this.data = getDataFromMap(data);
    }

    public String getToHtmlTable() {
        return "<table style=\" border: 1px black solid\">"
                + getTableHeaders()
                + getTableBody()
                + "</table>";
    }

    private String getTableBody() {
        StringBuilder body = new StringBuilder("<tbody>");
        for (String[] column : data) {
            body.append("<tr style=\"border : 1px black solid\">");
            for (String row : column) {
                body.append("<td style=\"border : 1px black solid\">")
                        .append(row)
                        .append("</td>");
            }
            body.append("</tr>");
        }
        return body + "</tbody>";
    }

    private String getTableHeaders() {
        StringBuilder headers = new StringBuilder("<thead><tr>");
        for (String header : this.headers) {
            headers.append("<th>")
                    .append(header)
                    .append("</th>");
        }
        return headers + "</tr></thead>";
    }

    private String[][] getDataFromMap(Map<String, String> oldDataFormat) {
        String[][] data = new String[oldDataFormat.size()][2];
        int indice = 0;
        for(String element : oldDataFormat.keySet()) {
            data[indice][0] = element;
            data[indice][1] = oldDataFormat.get(element);
            indice++;
        }
        return data;
    }
}
