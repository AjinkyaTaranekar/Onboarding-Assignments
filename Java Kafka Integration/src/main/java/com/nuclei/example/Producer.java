package com.nuclei.example;

import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.kafka.core.KafkaTemplate;

import java.util.Scanner;

public class Producer {
  
  public static void main(String[] args) {
    final AnnotationConfigApplicationContext context =
      new AnnotationConfigApplicationContext(Config.class);
    try (Scanner scanner = new Scanner(System.in)) {
      while (true){
        String message = scanner.nextLine();
        context.getBean(Producer.class).send(message, 40);
      }
    }
  }
  
  private final KafkaTemplate<Integer, String> template;
  
  public Producer(KafkaTemplate<Integer, String> template) {
    this.template = template;
  }
  
  public void send(String toSend, int key) {
    this.template.send("topic1", key, toSend);
  }
  
}