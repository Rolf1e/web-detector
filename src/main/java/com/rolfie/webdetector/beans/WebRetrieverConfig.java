package com.rolfie.webdetector.beans;

import com.rolfie.webdetector.retriever.infra.UrlHolder;
import com.rolfie.webdetector.retriever.infra.WebPageRetriever;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.io.IOException;
import java.util.Map;

@Configuration
public class WebRetrieverConfig {

    @Bean(name = "onePageMappedRetriever")
    public Map<Integer, Element> onePageMappedRetriever(WebPageRetriever webPageRetriever) {
        return webPageRetriever.mappingWebSite();
    }

    @Bean
    public WebPageRetriever onePageRetriever(Document document) {
        return new WebPageRetriever(document);
    }

    @Bean
    public Document siteRetriever(UrlHolder urlHolder) throws IOException {
        return Jsoup.connect(urlHolder.getUrl()).get();
    }

    @Bean
    public UrlHolder urlHolder(@Value("${url}") final String url) {
        return new UrlHolder(url);
    }
}
