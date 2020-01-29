package com.rolfie.webdetector.beans;

import com.rolfie.webdetector.retriever.infra.UrlHolder;
import io.webfolder.cdp.Launcher;
import io.webfolder.cdp.session.Session;
import io.webfolder.cdp.session.SessionFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class WebRetrieverConfig {

    @Bean(destroyMethod = "close")
    public Session sessionFactory(Launcher launcher,
                                  UrlHolder url) {
        SessionFactory sessionFactory = launcher.launch();

        return sessionFactory.create()
                .waitDocumentReady()
                .navigate(url.getUrl());
    }

    @Bean(destroyMethod = "kill")
    public Launcher launcher() {
        return new Launcher();
    }

    @Bean
    public UrlHolder urlHolder(@Value("${url}") final String url) {
        return new UrlHolder(url);
    }
}
