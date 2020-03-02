package com.rolfie.webdetector.retriever.infra.html;

import lombok.Getter;
import lombok.extern.slf4j.Slf4j;

@Getter
@Slf4j
public class Line implements HtmlLine {

    private String value;

    private Line(String value) {
        this.value = value;
    }

    public static Line create(String content) {
        return new Line(content);
    }

}
