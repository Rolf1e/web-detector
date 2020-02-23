package com.rolfie.webdetector.component;

import com.rolfie.webdetector.analyse.markup.ImgAnalyzer;
import com.rolfie.webdetector.analyse.markup.TextAnalyzer;
import com.rolfie.webdetector.retriever.JsoupRetriever;
import com.rolfie.webdetector.retriever.WebFolderRetriever;
import com.rolfie.webdetector.retriever.WebRetriever;
import com.rolfie.webdetector.retriever.infra.html.HtmlLine;
import com.rolfie.webdetector.retriever.infra.html.LineNumber;
import io.webfolder.cdp.Launcher;
import io.webfolder.cdp.session.Session;
import io.webfolder.cdp.session.SessionFactory;
import lombok.extern.slf4j.Slf4j;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;

import java.io.IOException;
import java.util.Map;

@Slf4j
public class Analyzer {

    private String url;

    public Analyzer(String url) {
        this.url = url;
    }

//    public Map<LineNumber, HtmlLine> analyze() {
//        log.info("Start text analyze");
//
//        final Launcher launcher = launcher();
//        try (SessionFactory factory = sessionFactory(launcher);
//             Session session = factory.create()) {
//            session.navigate(url);
//            session.waitDocumentReady();
//            TextAnalyzer analyzer = imageAnalyze(session);
//            return analyzer.found();
//        } finally {
//            launcher.kill();
//        }
//    }

    public Map<LineNumber, HtmlLine> imgAnalyze() throws IOException {
        log.info("Start image analyze");

        Document document = getDocument();
        TextAnalyzer analyzer = imageAnalyze(document);
        return analyzer.found();
    }

    private ImgAnalyzer imageAnalyze(Document document) {
        WebRetriever retriever = JsoupRetriever.getInstance(document);
        return new ImgAnalyzer(retriever.mappingBody());
    }


    private SessionFactory sessionFactory(Launcher launcher) {
        return launcher.launch();
    }

    private Launcher launcher() {
        return new Launcher();
    }

    private Document getDocument() throws IOException {
        return Jsoup.connect(url).get();
    }
}
