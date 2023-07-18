package com.woodwing.test.config;

import com.fasterxml.jackson.databind.DeserializationFeature;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

@Configuration
public class AppConfig {

  @Bean
  public ObjectMapper objectMapper() {
    ObjectMapper mapper = new ObjectMapper();
    mapper.enable(DeserializationFeature.USE_BIG_DECIMAL_FOR_FLOATS);
    return mapper;
  }

  @Bean
  public ExecutorService inferenceServiceExecutorService() {
    return Executors.newFixedThreadPool(1);
  }

}
