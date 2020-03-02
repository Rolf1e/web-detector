package com.rolfie.webdetector.component.job;

import com.rolfie.webdetector.mappings.dto.Job;
import org.junit.Assert;
import org.junit.Test;

import java.util.Arrays;
import java.util.List;

public class JobHandlerTest {

    @Test
    public void should_get_two_jobs() {
        List<Job> expected = Arrays.asList(new Job("job1", true), new Job("job2", true));
        List<Job> test = Arrays.asList(new Job("job1", true), new Job("job2", true), new Job("job3", false));
        JobHandler handler = new JobHandler(test);
        Assert.assertEquals(expected, handler.getJobsToDo());
    }

    @Test
    public void should_get_one_job() {
        List<Job> expected = Arrays.asList(new Job("job1", true));
        List<Job> test = Arrays.asList(new Job("job1", true), new Job("job1", true));
        JobHandler handler = new JobHandler(test);
        Assert.assertEquals(expected, handler.getJobsToDo());
    }

}
