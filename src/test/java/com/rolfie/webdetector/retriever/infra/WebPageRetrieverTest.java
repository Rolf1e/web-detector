package com.rolfie.webdetector.retriever.infra;

import lombok.extern.slf4j.Slf4j;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.junit.Test;

import java.io.IOException;

@Slf4j
public class WebPageRetrieverTest {


    @Test
    public void should_parse_web_site() {
        final String url = "http://youtube.com";
        try {
            Document document = Jsoup.connect(url).get();
            WebPageRetriever webPageRetriever = new WebPageRetriever(document);
            webPageRetriever.mappingWebSite();
        } catch (IOException e) {
            log.error("Can not load {} ", url, e);
        }

    }

}
