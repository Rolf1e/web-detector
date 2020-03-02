package com.rolfie.webdetector.mappings;

import com.rolfie.webdetector.component.job.JobHandler;
import com.rolfie.webdetector.component.response.json.FinalResponse;
import com.rolfie.webdetector.mappings.dto.Analyze;
import com.rolfie.webdetector.mappings.dto.Response;
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
    public Response analyze(@RequestBody Analyze analyze) {
        UrlHolder.getInstance(analyze.getUri());
        JobHandler jobHandler = new JobHandler(analyze.getJobs());
        return new FinalResponse(jobHandler.getJobsToDo())
                .getJson();
    }
}
