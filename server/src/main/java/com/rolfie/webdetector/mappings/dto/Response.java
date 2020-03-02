package com.rolfie.webdetector.mappings.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class Response {

    protected String uri;
    protected List<Line> alts;
    protected List<Line> accessibilite;

}
