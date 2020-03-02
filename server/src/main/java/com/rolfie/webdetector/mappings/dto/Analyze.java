package com.rolfie.webdetector.mappings.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class Analyze {

    private String uri;
    private List<Job> jobs;

}
