package com.rolfie.webdetector.component.response.json;

import com.rolfie.webdetector.analyse.AnalyzerHandler;
import com.rolfie.webdetector.mappings.UrlHolder;
import com.rolfie.webdetector.mappings.dto.Job;
import com.rolfie.webdetector.mappings.dto.Line;
import com.rolfie.webdetector.mappings.dto.Response;
import lombok.extern.slf4j.Slf4j;

import java.io.IOException;
import java.security.GeneralSecurityException;
import java.util.Collections;
import java.util.List;

@Slf4j
public class FinalResponse {

    private AnalyzerHandler analyzerHandler;
    private Response response;
    private List<Job> jobs;

    public FinalResponse(List<Job> jobs) throws IOException, GeneralSecurityException {
        analyzerHandler = new AnalyzerHandler(getUri());
        response = new Response();
        this.jobs = jobs;
    }

    public Response getJson() {
        response.setUri(getUri());
        for (Job job : jobs) {
            jobResolver(job);
        }
        return response;
    }

    private String getUri() {
        return UrlHolder.getInstance().getUrl();
    }

    private void jobResolver(Job job) {
        switch (job.getName()) {
            case "alt":
                response.setAlts(getAlts());
                break;
            case "accessibilite":
                response.setAccessibilite(getAccessibiliteWord());
                break;
            default:
                throw new IllegalStateException("No job selected");
        }
    }

    private List<Line> getAlts() {
        return new AltResponse(analyzerHandler.getImageAnalyzes())
                .getJson();
    }

    private List<Line> getAccessibiliteWord() {
        return new WordResponse(analyzerHandler.getAccessibiliteWordAnalyzes(), "accessibilite")
                .getJson();
    }
}
