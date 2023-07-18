package com.woodwing.test;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.context.properties.EnableConfigurationProperties;

@EnableConfigurationProperties
@SpringBootApplication
public class DistanceCalculatorToolApplication {

  public static void main(String[] args) {
    SpringApplication.run(DistanceCalculatorToolApplication.class, args);
  }

}
