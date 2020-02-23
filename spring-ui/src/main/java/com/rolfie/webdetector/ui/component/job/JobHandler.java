package com.rolfie.webdetector.ui.component.job;

import com.rolfie.webdetector.component.Analyzer;
import com.rolfie.webdetector.retriever.infra.UrlHolder;
import com.rolfie.webdetector.ui.component.response.json.AltResponse;
import com.rolfie.webdetector.ui.component.response.json.JsonResponse;
import lombok.extern.slf4j.Slf4j;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

@Slf4j
public class JobHandler {

    private List<Job> jobs;

    public JobHandler(List<Job> jobs) {
        this.jobs = jobs;
    }

    public List<Job> getJobsToDo() {
        List<Job> toDo = new ArrayList<>();

        for (Job job : jobs) {
            if (job.isActive() && !toDo.contains(job)) {
                toDo.add(job);
                continue;
            }
            log.warn("Job {} is either already in the list or not active", job.getName());
        }

        return toDo;
    }

    private JsonResponse jobResolver(Job job) {
        Analyzer analyzer = new Analyzer(UrlHolder.getInstance().getUrl());
        switch (job.getName()) {
            case "alt":
                return getAltResponse(analyzer);
            default:
                throw new IllegalStateException("No job selected");
        }
    }

    private JsonResponse getAltResponse(Analyzer analyzer) {
        try {
            return new AltResponse(analyzer.imgAnalyze());
        } catch (IOException e) {
            log.error("Can not get Data for alt job ", e);
            return null;
        }
    }

}
