package com.rolfie.webdetector.ui.component;

public class IndexForm {

    public static String getForm() {
        return "<form action=\"/resolve?url=\">" +
                "<label for=\"url\">URL :</label>" +
                "<input type=\"text\" name=\"url\">" +
                "<input type=\"submit\" value=\"Analyze\">" +
                "</form>";
    }

}
