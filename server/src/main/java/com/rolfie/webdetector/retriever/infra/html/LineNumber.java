package com.rolfie.webdetector.retriever.infra.html;

import lombok.Getter;

@Getter
public class LineNumber {

    private String number;

    private LineNumber(String number) {
        this.number = number;
    }

    public static LineNumber create(String number) {
        return new LineNumber(number);
    }
}
