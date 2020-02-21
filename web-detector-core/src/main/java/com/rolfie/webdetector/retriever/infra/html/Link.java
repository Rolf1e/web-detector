package com.rolfie.webdetector.retriever.infra.html;

import com.rolfie.webdetector.analyse.infra.pattern.regex.PatternResolver;
import lombok.Getter;
import lombok.extern.slf4j.Slf4j;

@Slf4j
public class Link implements HtmlLine {

    @Getter
    private String value;

    public Link(String value) {
        this.value = value;
    }

    public static Link extractLink(String content) {
        PatternResolver resolver = new PatternResolver("src=\".*\"");
        try {
            return new Link(resolver.extractContent(content));
        } catch (PatternResolver.RegexException e) {
            log.error("Impossible to extract link from {}", content, e);
        }
        return new Link(content);
    }
}
