package com.rolfie.webdetector.ui.component.html;

import org.junit.Assert;
import org.junit.Test;

import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class HtmlTableTest {

    @Test
    public void should_build_table() {
        final String expected = "<table><thead><tr><th>Name</th><th>Nickname</th><th>Age</th></tr></thead><tbody><tr><td>Tigran</td><td>Rolfie</td><td>20</td></tr><tr><td>Emma</td><td>Ephrimes</td><td>21</td></tr></tbody></table>";

        List<String> headers = Arrays.asList("Name", "Nickname", "Age");
        String[][] data = new String[2][3];
            data[0][0] = "Tigran";
            data[0][1] = "Rolfie";
            data[0][2] = "20";

            data[1][0] = "Emma";
            data[1][1] = "Ephrimes";
            data[1][2] = "21";

        HtmlTable htmlTable = new HtmlTable(headers, data);

        Assert.assertEquals(expected, htmlTable.getToHtmlTable());
    }

    @Test
    public void should_build_table_from_map(){
        final String expected = "<table><thead><tr><th>Line</th><th>Nickname</th></tr></thead><tbody><tr><td>1</td><td>Rolfie</td></tr><tr><td>2</td><td>Ephrimes</td></tr></tbody></table>";
        List<String> headers = Arrays.asList("Line", "Nickname");
        Map<String, String> data = new HashMap<>();
        data.put("1", "Rolfie");
        data.put("2", "Ephrimes");

        HtmlTable htmlTable = new HtmlTable(headers, data);

        Assert.assertEquals(expected, htmlTable.getToHtmlTable());
    }

}
