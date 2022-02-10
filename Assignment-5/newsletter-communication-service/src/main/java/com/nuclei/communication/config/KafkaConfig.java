package com.nuclei.communication.config;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.kafka.support.serializer.ErrorHandlingDeserializer;
import org.springframework.kafka.support.serializer.JsonSerializer;
import org.springframework.kafka.support.serializer.JsonDeserializer;
import com.nuclei.communication.dto.request.EmailDto;
import org.apache.kafka.clients.consumer.ConsumerConfig;
import org.apache.kafka.clients.producer.ProducerConfig;
import org.apache.kafka.common.serialization.IntegerDeserializer;
import org.apache.kafka.common.serialization.IntegerSerializer;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.kafka.annotation.EnableKafka;
import org.springframework.kafka.config.ConcurrentKafkaListenerContainerFactory;
import org.springframework.kafka.core.*;

import java.util.HashMap;
import java.util.Map;

@Configuration
@EnableKafka
public class KafkaConfig {
  
  @Value("${spring.kafka.bootstrap-servers}")
  private String bootstrapServers;
  
  @Bean
  ConcurrentKafkaListenerContainerFactory<Integer, EmailDto>
  kafkaListenerContainerFactory(ConsumerFactory<Integer, EmailDto> consumerFactory) {
    ConcurrentKafkaListenerContainerFactory<Integer, EmailDto> factory =
        new ConcurrentKafkaListenerContainerFactory<>();
    factory.setConsumerFactory(consumerFactory);
    return factory;
  }
  
  @Bean
  public ConsumerFactory<Integer, EmailDto> consumerFactory() {
    return new DefaultKafkaConsumerFactory<>(consumerProps(),
        new IntegerDeserializer(), new JsonDeserializer<EmailDto>());
  }
  
  private Map<String, Object> consumerProps() {
    Map<String, Object> props = new HashMap<>();
    props.put(ProducerConfig.BOOTSTRAP_SERVERS_CONFIG, bootstrapServers);
    props.put(ConsumerConfig.AUTO_OFFSET_RESET_CONFIG, "earliest");
    props.put(JsonDeserializer.TRUSTED_PACKAGES, "com.nuclei.communication");
    props.put(ConsumerConfig.VALUE_DESERIALIZER_CLASS_CONFIG, ErrorHandlingDeserializer.class);
    props.put(ConsumerConfig.KEY_DESERIALIZER_CLASS_CONFIG, ErrorHandlingDeserializer.class);
    props.put(ErrorHandlingDeserializer.KEY_DESERIALIZER_CLASS, JsonDeserializer.class);
    props.put(ErrorHandlingDeserializer.VALUE_DESERIALIZER_CLASS, JsonDeserializer.class.getName());
    props.put(JsonDeserializer.KEY_DEFAULT_TYPE, Integer.class);
    props.put(JsonDeserializer.VALUE_DEFAULT_TYPE, EmailDto.class);
    // ...
    return props;
  }
  
  
  @Bean
  public ProducerFactory<Integer, EmailDto> producerFactory() {
    return new DefaultKafkaProducerFactory<>(senderProps(),
        new IntegerSerializer(), new JsonSerializer<EmailDto>());
  }
  
  private Map<String, Object> senderProps() {
    Map<String, Object> props = new HashMap<>();
    props.put(ProducerConfig.BOOTSTRAP_SERVERS_CONFIG, bootstrapServers);
    props.put(ProducerConfig.LINGER_MS_CONFIG, 10);
    props.put(ProducerConfig.KEY_SERIALIZER_CLASS_CONFIG, IntegerSerializer.class);
    props.put(ProducerConfig.VALUE_SERIALIZER_CLASS_CONFIG, JsonSerializer.class);
    props.put(JsonDeserializer.TRUSTED_PACKAGES, "com.nuclei.communication");
    //...
    return props;
  }
  
  @Bean
  public KafkaTemplate<Integer, EmailDto> kafkaTemplate(
      ProducerFactory<Integer, EmailDto> producerFactory) {
    return new KafkaTemplate<>(producerFactory);
  }
  
}