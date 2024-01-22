package com.andpostman.packagerouter.config;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.web.reactive.function.client.WebClient;

@Configuration
public class WebClientConfig {

    @Value("${web-client.package-processor.url}")
    private String packageProcessorIp;

    @Bean
    public WebClient callPackageProcessor(){
        return WebClient.builder()
                .baseUrl(packageProcessorIp)
                .defaultHeader(HttpHeaders.CONTENT_TYPE, MediaType.APPLICATION_JSON_VALUE)
                .build();
    }
}
