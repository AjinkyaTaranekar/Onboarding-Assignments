package com.nuclei.example.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import springfox.documentation.builders.RequestHandlerSelectors;
import springfox.documentation.service.ApiInfo;
import springfox.documentation.service.Contact;
import springfox.documentation.spi.DocumentationType;
import springfox.documentation.spring.web.plugins.Docket;

import java.util.Collections;

/**
 * The type Swagger config.
 */
@Configuration
public class SwaggerConfig {
  
  /**
   * Api docket.
   *
   * @return the docket
   */
  @Bean
  public Docket api () {
    return new Docket(DocumentationType.SWAGGER_2)
        .apiInfo(metaData())
        .select()
        .apis(RequestHandlerSelectors.basePackage("com.nuclei.example"))
        .build();
  }
  
  private ApiInfo metaData () {
    return new ApiInfo(
        "Inventory Service",
        "API serves all controller related to inventory.",
        "1.0",
        "http://www.localhost.com/tos",
        new Contact("Ajinkya Helpline", "www.localhost.com", "ajinkyataranekar" +
            "@gmail.com"),
        "License of API", "http://www.localhost.com/license", Collections.emptyList());
  }
}