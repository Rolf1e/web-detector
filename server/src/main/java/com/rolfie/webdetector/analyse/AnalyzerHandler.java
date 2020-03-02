package com.rolfie.webdetector.analyse;

import com.rolfie.webdetector.analyse.markup.ImgAnalyzer;
import com.rolfie.webdetector.analyse.markup.TextAnalyzer;
import com.rolfie.webdetector.analyse.markup.WordAnalyzer;
import com.rolfie.webdetector.retriever.JsoupRetriever;
import com.rolfie.webdetector.retriever.WebRetriever;
import com.rolfie.webdetector.retriever.infra.cacerts.CacertsUtils;
import com.rolfie.webdetector.retriever.infra.html.HtmlLine;
import com.rolfie.webdetector.retriever.infra.html.LineNumber;
import io.webfolder.cdp.Launcher;
import io.webfolder.cdp.session.SessionFactory;
import lombok.extern.slf4j.Slf4j;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;

import java.io.IOException;
import java.security.GeneralSecurityException;
import java.util.Map;

import static com.rolfie.webdetector.analyse.infra.pattern.regex.PatternHolder.ACCESSIBLE_PATTERN;

@Slf4j
public class AnalyzerHandler {

    private final String url;
    private WebRetriever retriever;

    public AnalyzerHandler(String url) throws IOException, GeneralSecurityException {
        this.url = url;
        retriever = getJsoupRetriever();
    }

    public Map<LineNumber, HtmlLine> getImageAnalyzes() throws IOException, GeneralSecurityException {
        log.info("Start image analyze");

        TextAnalyzer analyzer = imageAnalyze();
        return analyzer.found();
    }

    public Map<LineNumber, HtmlLine> getAccessibiliteWordAnalyzes() throws IOException, GeneralSecurityException {
        log.info("Start accessibilite word analyze");
        TextAnalyzer analyzer = accessibiliteWordAnalyze();
        return analyzer.found();
    }

    private TextAnalyzer imageAnalyze() throws IOException, GeneralSecurityException {
//        WebRetriever retriever = getJsoupRetriever();
        return new ImgAnalyzer(retriever.mappingBody());
    }

    private TextAnalyzer accessibiliteWordAnalyze() throws IOException, GeneralSecurityException {
//        WebRetriever retriever = getJsoupRetriever();
        return new WordAnalyzer(retriever.mappingBody(), ACCESSIBLE_PATTERN.getPattern());
    }

    private WebRetriever getJsoupRetriever() throws IOException, GeneralSecurityException {
        return new JsoupRetriever(getDocument());
    }

    private SessionFactory sessionFactory(Launcher launcher) {
        return launcher.launch();
    }

    private Launcher launcher() {
        return new Launcher();
    }

    private Document getDocument() throws IOException, GeneralSecurityException {
        return Jsoup.connect(url)
                .sslSocketFactory(CacertsUtils.jsoupSocketFactory())
                .get();
    }
}
