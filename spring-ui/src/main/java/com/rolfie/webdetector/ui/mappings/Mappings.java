package com.rolfie.webdetector.ui.mappings;

import com.rolfie.webdetector.component.Analyzer;
import com.rolfie.webdetector.ui.component.IndexForm;
import com.rolfie.webdetector.ui.component.html.HtmlTable;
import lombok.extern.slf4j.Slf4j;
import com.rolfie.webdetector.ui.component.response.json.JsonResponse;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.io.IOException;
import java.util.Arrays;

@Slf4j
@RestController
public class Mappings {

    @GetMapping("/")
    public String formIndex() {
        return IndexForm.getForm();
    }

    @GetMapping("/resolve")
    public String resolver(@RequestParam(value = "value", defaultValue = "https://www.pictoaccess.fr/") String url) {
        Analyzer analyzer = new Analyzer(url);
        HtmlTable htmlTable = null;
        try {
            htmlTable = new HtmlTable(Arrays.asList("Line", "Link"), analyzer.imgAnalyze());
        } catch (IOException e) {
            return "Can not retrieve web page from " + url + "\n" + e;
        }
        return htmlTable.getToHtmlTable();
    }

    @GetMapping("/front")
    public String front(@RequestParam(value = "value", defaultValue = "https://www.pictoaccess.fr/") String url) {
        return JsonResponse.getJson();
    }
}
