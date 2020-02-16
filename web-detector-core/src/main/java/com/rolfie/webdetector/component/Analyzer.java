package com.rolfie.webdetector.component;

import com.rolfie.webdetector.analyse.markup.ImgAnalyzer;
import com.rolfie.webdetector.retriever.infra.WebPageRetriever;
import io.webfolder.cdp.Launcher;
import io.webfolder.cdp.session.Session;
import io.webfolder.cdp.session.SessionFactory;
import lombok.extern.slf4j.Slf4j;

@Slf4j
public class Analyzer {


    private String url;

    public Analyzer(String url) {
        this.url = url;
    }

    public String analyze() {
        log.info("Start text analyze");
        Session session = sessionFactory(launcher(), url);
        WebPageRetriever retriever = new WebPageRetriever(session);
        ImgAnalyzer analyzer = new ImgAnalyzer(retriever.mappingHead());
        return String.valueOf(analyzer.foundErrors());
    }

    public Session sessionFactory(Launcher launcher,
                                  String url) {
        SessionFactory sessionFactory = launcher.launch();

        return sessionFactory.create()
                .waitDocumentReady()
                .navigate(url);
    }

    public Launcher launcher() {
        return new Launcher();
    }




}
