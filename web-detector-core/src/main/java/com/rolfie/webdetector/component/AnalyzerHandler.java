package com.rolfie.webdetector.component;

import com.rolfie.webdetector.analyse.infra.pattern.regex.PatternHolder;
import com.rolfie.webdetector.analyse.markup.ImgAnalyzer;
import com.rolfie.webdetector.analyse.markup.TextAnalyzer;
import com.rolfie.webdetector.analyse.markup.WordAnalyzer;
import com.rolfie.webdetector.retriever.JsoupRetriever;
import com.rolfie.webdetector.retriever.WebRetriever;
import com.rolfie.webdetector.retriever.infra.html.HtmlLine;
import com.rolfie.webdetector.retriever.infra.html.LineNumber;
import io.webfolder.cdp.Launcher;
import io.webfolder.cdp.session.SessionFactory;
import lombok.extern.slf4j.Slf4j;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;

import javax.net.ssl.SSLContext;
import javax.net.ssl.SSLSocketFactory;
import javax.net.ssl.TrustManager;
import javax.net.ssl.X509TrustManager;
import java.io.IOException;
import java.security.KeyManagementException;
import java.security.NoSuchAlgorithmException;
import java.security.cert.X509Certificate;
import java.util.Map;

@Slf4j
public class AnalyzerHandler {

    private final String url;

    public AnalyzerHandler(String url) {
        this.url = url;
    }

    public Map<LineNumber, HtmlLine> imageAnalyze() throws IOException {
        log.info("Start image analyze");

        Document document = getDocument();
        TextAnalyzer analyzer = imageAnalyze(document);
        return analyzer.found();
    }

    public Map<LineNumber, HtmlLine> accessibiliteWordAnalyze() throws IOException {
        log.info("Start accessibilite word analyze");

        Document document = getDocument();
        TextAnalyzer analyzer = accessibiliteWordAnalyze(document);
        return analyzer.found();
    }

    private TextAnalyzer imageAnalyze(Document document) {
        WebRetriever retriever = new JsoupRetriever(document);
        return new ImgAnalyzer(retriever.mappingBody());
    }

    private TextAnalyzer accessibiliteWordAnalyze(Document document) throws IOException {
        WebRetriever retriever = new JsoupRetriever(document);
        return new WordAnalyzer(retriever.mappingBody(), PatternHolder.accessiblePattern);
    }


    private SessionFactory sessionFactory(Launcher launcher) {
        return launcher.launch();
    }

    private Launcher launcher() {
        return new Launcher();
    }

    private Document getDocument() throws IOException {
        return Jsoup.connect(url)
                .sslSocketFactory(socketFactory())
                .get();
    }

    private SSLSocketFactory socketFactory() {
        TrustManager[] trustAllCerts = new TrustManager[]{new X509TrustManager() {
            public java.security.cert.X509Certificate[] getAcceptedIssuers() {
                return null;
            }

            public void checkClientTrusted(X509Certificate[] certs, String authType) {
            }

            public void checkServerTrusted(X509Certificate[] certs, String authType) {
            }
        }};

        try {
            SSLContext sslContext = SSLContext.getInstance("TLS");
            sslContext.init(null, trustAllCerts, new java.security.SecureRandom());
            return sslContext.getSocketFactory();
        } catch (NoSuchAlgorithmException | KeyManagementException e) {
            throw new RuntimeException("Failed to create a SSL socket factory", e);
        }
    }
}
