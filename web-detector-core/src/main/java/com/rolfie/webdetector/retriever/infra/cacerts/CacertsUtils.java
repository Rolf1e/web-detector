package com.rolfie.webdetector.retriever.infra.cacerts;

import lombok.experimental.UtilityClass;

import javax.net.ssl.SSLContext;
import javax.net.ssl.SSLSocketFactory;
import javax.net.ssl.TrustManager;
import javax.net.ssl.X509TrustManager;
import java.security.GeneralSecurityException;
import java.security.KeyManagementException;
import java.security.NoSuchAlgorithmException;
import java.security.SecureRandom;
import java.security.cert.X509Certificate;

@UtilityClass
public class CacertsUtils {

    public static SSLSocketFactory jsoupSocketFactory() throws GeneralSecurityException {
        TrustManager[] trustAllCerts = new TrustManager[]{new X509TrustManager() {
            @Override
            public X509Certificate[] getAcceptedIssuers() {
                return new X509Certificate[0];
            }

            @Override
            public void checkClientTrusted(X509Certificate[] certs, String authType) {
                //Ignore SSLCertificats
            }

            @Override
            public void checkServerTrusted(X509Certificate[] certs, String authType) {
                //Ignore SSLCertificats
            }
        }};

        try {
            SSLContext sslContext = SSLContext.getInstance("TLS");
            sslContext.init(null, trustAllCerts, new SecureRandom());
            return sslContext.getSocketFactory();
        } catch (NoSuchAlgorithmException | KeyManagementException e) {
            throw new GeneralSecurityException("Failed to create a SSL socket factory", e);
        }
    }
}
