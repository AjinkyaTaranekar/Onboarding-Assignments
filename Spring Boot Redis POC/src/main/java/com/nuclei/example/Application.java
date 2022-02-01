package com.nuclei.example;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cache.annotation.EnableCaching;

/**
 * The type Application.
 */
@SpringBootApplication
@EnableCaching
public class Application {
  
  /**
   * The entry point of application.
   *
   * @param args the input arguments
   */
  public static void main (String[] args) {
    SpringApplication.run(Application.class, args);
  }
  
}
