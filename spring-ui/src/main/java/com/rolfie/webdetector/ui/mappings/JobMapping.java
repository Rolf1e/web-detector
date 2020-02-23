package com.rolfie.webdetector.ui.mappings;

import com.rolfie.webdetector.retriever.infra.UrlHolder;
import com.rolfie.webdetector.ui.component.job.JobHandler;
import com.rolfie.webdetector.ui.component.response.json.FinalResponse;
import com.rolfie.webdetector.ui.controller.AnalyzeController;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@Slf4j
@RestController
public class JobMapping {

    @PostMapping(value = "/resolve",
            consumes = "application/json",
            produces = "application/json")
    public String analyze(@RequestBody AnalyzeController analyzeController) {
        UrlHolder.getInstance(analyzeController.getUri());
        final JobHandler handler = new JobHandler(analyzeController.getJobs());
        return new FinalResponse(handler)
                .getJson();
    }
}
