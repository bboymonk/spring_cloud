package com.wjb.elasticsearch.config;

import org.apache.http.HttpHost;
import org.elasticsearch.client.RestClient;
import org.elasticsearch.client.RestClientBuilder;
import org.elasticsearch.client.RestHighLevelClient;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.util.stream.Collectors;
import java.util.stream.Stream;


@Configuration
public class ElasticSearchRestClient {

    @Value("${spring.data.elasticsearch.cluster-nodes}")
    private String[] ips;

    @Bean
    public RestHighLevelClient client() {
        HttpHost[] httpHosts = (HttpHost[]) Stream.of(ips).map(this::createHttpHost).collect(Collectors.toList()).toArray();
        RestClientBuilder builder = RestClient.builder(httpHosts);
        return new RestHighLevelClient(builder);
    }

    private HttpHost createHttpHost(String ip) {
        return HttpHost.create(ip);
    }

}
