package com.example.EsimBack;
import org.apache.hc.client5.http.impl.classic.CloseableHttpClient;
import org.apache.hc.client5.http.impl.classic.HttpClients;
import org.apache.hc.client5.http.impl.io.PoolingHttpClientConnectionManager;
import org.apache.hc.client5.http.impl.io.PoolingHttpClientConnectionManagerBuilder;
import org.apache.hc.client5.http.ssl.NoopHostnameVerifier;
import org.apache.hc.client5.http.ssl.SSLConnectionSocketFactory;
import org.apache.hc.core5.ssl.SSLContextBuilder;
import org.apache.hc.core5.ssl.TrustStrategy;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.client.HttpComponentsClientHttpRequestFactory;
import org.springframework.web.client.RestTemplate;

import javax.net.ssl.HostnameVerifier;
import javax.net.ssl.SSLContext;
import java.io.File;
import java.security.cert.X509Certificate;

@Configuration
public class RestTemplateConfig {

    @Bean
    public RestTemplate restTemplate() throws Exception {
        // Créer un SSLContext personnalisé
        SSLContext sslContext = SSLContextBuilder.create()
                .loadTrustMaterial(new File("C:\\Users\\USER\\Desktop\\EsimBack\\src\\main\\resources\\bmwTrustStore.jks"), "chirazisimm12".toCharArray()) // Charger le TrustStore
                .build();
        HostnameVerifier allowAllHosts = new NoopHostnameVerifier();

        // Créer une instance de SSLConnectionSocketFactory
        SSLConnectionSocketFactory sslConnectionSocketFactory = new SSLConnectionSocketFactory(sslContext ,allowAllHosts);

        // Créer un gestionnaire de connexions avec le support SSL
        PoolingHttpClientConnectionManager connectionManager = PoolingHttpClientConnectionManagerBuilder.create()
                .setSSLSocketFactory(sslConnectionSocketFactory)  // Configurer le support SSL
                .build();

        // Créer un client HTTP CloseableHttpClient avec SSL
        CloseableHttpClient httpClient = HttpClients.custom()
                .setConnectionManager(connectionManager)
                .build();

        // Créer une instance de HttpComponentsClientHttpRequestFactory
        HttpComponentsClientHttpRequestFactory requestFactory = new HttpComponentsClientHttpRequestFactory(httpClient);

        // Retourner une instance de RestTemplate
        return new RestTemplate(requestFactory);
    }
}





















