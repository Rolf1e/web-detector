package com.rolfie.webdetector.ui.mappings;

import com.rolfie.webdetector.analyse.markup.ImgAnalyzer;
import com.rolfie.webdetector.component.Analyzer;
import com.rolfie.webdetector.ui.component.IndexForm;
import com.rolfie.webdetector.ui.component.html.HtmlTable;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.Arrays;

@RestController
public class Mappings {

    @GetMapping("/")
    public String formIndex() {
        return IndexForm.getForm();
    }

    @GetMapping("/resolve")
    public String resolver(@RequestParam(value = "value", defaultValue = "http://www.ville-dunkerque.fr/") String url) {
        Analyzer analyzer = new Analyzer(url);
        HtmlTable htmlTable = new HtmlTable(Arrays.asList("Line", "Link"), analyzer.analyze());
        return htmlTable.getToHtmlTable();
    }

}
