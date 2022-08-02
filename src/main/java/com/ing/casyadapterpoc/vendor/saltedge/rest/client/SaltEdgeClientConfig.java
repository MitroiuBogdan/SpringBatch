package com.ing.casyadapterpoc.vendor.saltedge.rest.client;

import com.ing.casyadapterpoc.vendor.saltedge.properties.SaltEdgeClientProperties;
import io.netty.handler.ssl.SslContext;
import io.netty.handler.ssl.SslContextBuilder;
import io.netty.handler.ssl.util.InsecureTrustManagerFactory;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.client.reactive.ReactorClientHttpConnector;
import org.springframework.web.reactive.function.client.WebClient;
import reactor.netty.http.client.HttpClient;

import javax.net.ssl.SSLException;

@Configuration
public class SaltEdgeClientConfig {

    private static final String APP_ID_HEADER = "App-id";
    private static final String SC_HEADER = "Secret";

    @Bean
    public WebClient saltEdgeWebClient(SaltEdgeClientProperties properties) throws SSLException {
        SslContext sslContext = SslContextBuilder
                .forClient()
                .trustManager(InsecureTrustManagerFactory.INSTANCE)
                .build();
        HttpClient httpClient = HttpClient.create().secure(t -> t.sslContext(sslContext));

        return WebClient.builder()
                .defaultHeader(APP_ID_HEADER, properties.getApplicationId())
                .defaultHeader(SC_HEADER, properties.getSecret())
                .defaultHeader("Accept", "application/json")
                .defaultHeader("Content-Type", "application/json")
                .clientConnector(new ReactorClientHttpConnector(httpClient))
                .build().mutate().baseUrl(properties.getHost()).build();
    }
}
