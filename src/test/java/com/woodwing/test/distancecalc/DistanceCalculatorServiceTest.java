package com.woodwing.test.distancecalc;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.woodwing.test.distancecalc.dto.DistanceCalculatorDto;
import com.woodwing.test.distancecalc.dto.DistanceCalculatorItemDto;
import com.woodwing.test.distancecalc.dto.DistanceUnit;
import lombok.SneakyThrows;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.math.BigDecimal;

import static org.assertj.core.api.Assertions.assertThat;

@SpringBootTest
public class DistanceCalculatorServiceTest {

  @Autowired
  private DistanceCalculatorService distanceCalculatorService;

  @Autowired
  private ObjectMapper objectMapper;

  @SneakyThrows
  @Test
  public void givenValidInputThenReturnsCorrectCalculations() {
    String jsonPayload = """
        {
          "outputUnit": "METER",
          "inputDistances": [
            {
              "distanceValue": 3,
              "distanceUnit": "YARD"
            },
            {
              "distanceValue": 5,
              "distanceUnit": "METER"
            }
          ]
        }
        """;
    DistanceCalculatorDto distanceCalculatorDto = objectMapper.readValue(jsonPayload, DistanceCalculatorDto.class);
    DistanceCalculatorItemDto results = distanceCalculatorService.calculateDistances(distanceCalculatorDto);
    assertThat(results.getDistanceValue()).isEqualTo(new BigDecimal("7.74320"));
    assertThat(results.getDistanceUnit()).isEqualTo(DistanceUnit.METER);
  }

  @SneakyThrows
  @Test
  public void givenValidInputThenReturnsCorrectYardCalculations() {
    String jsonPayload = """
        {
          "outputUnit": "YARD",
          "inputDistances": [
            {
              "distanceValue": 3,
              "distanceUnit": "YARD"
            },
            {
              "distanceValue": 5,
              "distanceUnit": "METER"
            }
          ]
        }
        """;
    DistanceCalculatorDto distanceCalculatorDto = objectMapper.readValue(jsonPayload, DistanceCalculatorDto.class);
    DistanceCalculatorItemDto results = distanceCalculatorService.calculateDistances(distanceCalculatorDto);
    assertThat(results.getDistanceValue()).isEqualTo(new BigDecimal("8.468040952"));
    assertThat(results.getDistanceUnit()).isEqualTo(DistanceUnit.YARD);
  }

}
