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

        final Launcher launcher = launcher();
        try(SessionFactory factory = sessionFactory(launcher);
                Session session = factory.create()){
            session.navigate(url);
            session.waitDocumentReady();
            WebPageRetriever retriever = new WebPageRetriever(session);
            ImgAnalyzer analyzer = new ImgAnalyzer(retriever.mappingBody());
            return String.valueOf(analyzer.foundErrors());
        } finally {
            launcher.kill();
        }
    }

    private SessionFactory sessionFactory(Launcher launcher) {
        return launcher.launch();
    }

    private Launcher launcher() {
        return new Launcher();
    }
}
