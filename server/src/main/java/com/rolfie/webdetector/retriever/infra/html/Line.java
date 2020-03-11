package com.rolfie.webdetector.retriever.infra.html;

import lombok.Getter;
import lombok.extern.slf4j.Slf4j;

@Getter
@Slf4j
public class Line implements HtmlLine {

    private String context;

    private Line(String context) {
        this.context = context;
    }

    public static Line create(String content) {
        return new Line(content);
    }

}
