package com.rolfie.webdetector.ui.controller;

import com.rolfie.webdetector.ui.component.job.Job;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class AnalyzeController {

    private String uri;
    private List<Job> jobs;

}
