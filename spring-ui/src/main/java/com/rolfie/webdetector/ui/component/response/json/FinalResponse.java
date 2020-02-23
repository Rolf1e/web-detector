package com.rolfie.webdetector.ui.component.response.json;

import com.rolfie.webdetector.component.Analyzer;
import com.rolfie.webdetector.retriever.infra.UrlHolder;
import com.rolfie.webdetector.ui.component.job.Job;
import com.rolfie.webdetector.ui.dto.Line;
import com.rolfie.webdetector.ui.dto.Response;
import lombok.extern.slf4j.Slf4j;

import java.io.IOException;
import java.util.List;

@Slf4j
public class FinalResponse {

    private Analyzer analyzer;
    private Response response;
    private List<Job> jobs;

    public FinalResponse(List<Job> jobs) {
        analyzer = new Analyzer(getUri());
        response = new Response();
        this.jobs = jobs;
    }

    public Response getJson() {
        response.setUri(getUri());
        for(Job job : jobs) {
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
            default:
                throw new IllegalStateException("No job selected");
        }
    }

    private List<Line> getAlts() {
        try {
            return new AltResponse(analyzer.imgAnalyze())
                    .getJson();
        } catch (IOException e) {
            log.error("Can not get Data for alt job ", e);
            return null;
        }
    }


}
