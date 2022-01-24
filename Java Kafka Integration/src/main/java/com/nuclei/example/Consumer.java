package com.nuclei.example;

import org.springframework.kafka.annotation.KafkaListener;

public class Consumer {
  
  @KafkaListener(id = "listen", topics = "topic1")
  public void listen(String input) {
    System.out.printf("Got %s%n",input);
  }
  
}