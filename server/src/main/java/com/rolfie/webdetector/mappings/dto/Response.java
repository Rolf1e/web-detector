package com.rolfie.webdetector.mappings.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class Response {

    private String uri;
    private List<Line> alts;
    private List<Line> accessibilite;

}
