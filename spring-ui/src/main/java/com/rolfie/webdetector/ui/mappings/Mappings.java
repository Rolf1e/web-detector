package com.rolfie.webdetector.ui.mappings;

import com.rolfie.webdetector.component.Analyzer;
import com.rolfie.webdetector.retriever.infra.UrlHolder;
import com.rolfie.webdetector.ui.component.IndexForm;
import com.rolfie.webdetector.ui.component.response.json.JsonResponse;
import lombok.extern.slf4j.Slf4j;
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
        UrlHolder urlHolder = UrlHolder.getInstance(url);
        Analyzer analyzer = new Analyzer(urlHolder.getUrl());
        try {
            return new JsonResponse(analyzer.imgAnalyze()).getJson();
        } catch (IOException e) {
            return "Can not retrieve web page from " + urlHolder.getUrl() + "\n" + e;
        }
    }
}
