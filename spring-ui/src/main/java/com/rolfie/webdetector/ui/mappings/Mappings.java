package com.rolfie.webdetector.ui.mappings;

import com.rolfie.webdetector.component.Analyzer;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class Mappings {

    @GetMapping("/resolve")
    public String resolver(@RequestParam(value = "value", defaultValue = "http://www.ville-dunkerque.fr/") String url) {
        Analyzer analyzer = new Analyzer(url);
        return analyzer.analyze();
    }
}
