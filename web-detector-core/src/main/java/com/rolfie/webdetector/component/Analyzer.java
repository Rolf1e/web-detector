package com.rolfie.webdetector.component;

import com.rolfie.webdetector.analyse.markup.ImgAnalyzer;
import com.rolfie.webdetector.retriever.infra.WebPageRetriever;
import io.webfolder.cdp.Launcher;
import io.webfolder.cdp.session.Session;
import io.webfolder.cdp.session.SessionFactory;
import lombok.extern.slf4j.Slf4j;

import java.util.Map;

@Slf4j
public class Analyzer {

    private String url;

    public Analyzer(String url) {
        this.url = url;
    }

    public Map<String, String> analyze() {
        log.info("Start text analyze");

        final Launcher launcher = launcher();
        try(SessionFactory factory = sessionFactory(launcher);
                Session session = factory.create()){
            session.navigate(url);
            session.waitDocumentReady();
            ImgAnalyzer analyzer = imageAnalyze(session);
            return analyzer.foundErrors();
        } finally {
            launcher.kill();
        }
    }

    private ImgAnalyzer imageAnalyze(Session session) {
        WebPageRetriever retriever = new WebPageRetriever(session);
        return new ImgAnalyzer(retriever.mappingBody());
    }

    private SessionFactory sessionFactory(Launcher launcher) {
        return launcher.launch();
    }

    private Launcher launcher() {
        return new Launcher();
    }
}
