package com.nuclei.example.config;

import org.elasticsearch.client.RestHighLevelClient;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.elasticsearch.client.ClientConfiguration;
import org.springframework.data.elasticsearch.client.RestClients;
import org.springframework.data.elasticsearch.core.ElasticsearchOperations;
import org.springframework.data.elasticsearch.core.ElasticsearchRestTemplate;
import org.springframework.data.elasticsearch.repository.config.EnableElasticsearchRepositories;

@Configuration
@EnableElasticsearchRepositories(basePackages = "com.nuclei.example.repository")
@ComponentScan(basePackages = { "com.nuclei.example.service" })
public class ElasticConfig {
  
  @Value("${elasticsearch.url}")
  private String elasticSearchUrl;
  
  @Bean
  public RestHighLevelClient client() {
    ClientConfiguration clientConfiguration
        = ClientConfiguration.builder()
        .connectedTo(elasticSearchUrl)
        .build();
    
    return RestClients.create(clientConfiguration).rest();
  }
  
  @Bean
  public ElasticsearchOperations elasticsearchTemplate() {
    return new ElasticsearchRestTemplate(client());
  }
}