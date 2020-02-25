package com.rolfie.webdetector.ui.component.job;

import lombok.extern.slf4j.Slf4j;

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
}
