package com.woodwing.test.distancecalc;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.woodwing.test.distancecalc.dto.DistanceCalculatorDto;
import com.woodwing.test.distancecalc.dto.DistanceCalculatorItemDto;
import lombok.SneakyThrows;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;

import java.math.BigDecimal;

import static org.assertj.core.api.Assertions.assertThat;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@AutoConfigureMockMvc
@SpringBootTest
public class DistanceCalculatorControllerTest {

  @Autowired
  private MockMvc mvc;

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
    mvc.perform(post("/api/distance/calculator").content(jsonPayload).contentType(MediaType.APPLICATION_JSON))
        .andExpect(status().isOk())
        .andExpect(content().contentTypeCompatibleWith(MediaType.APPLICATION_JSON));
  }

  @SneakyThrows
  @Test
  public void givenInvalidInputDueToEmptyInputDistancesThenReturns400() {
    String jsonPayload = """
        {
          "outputUnit": "METER",
          "inputDistances": [

          ]
        }
        """;
    mvc.perform(post("/api/distance/calculator").content(jsonPayload).contentType(MediaType.APPLICATION_JSON))
        .andExpect(status().is4xxClientError())
        .andExpect(content().contentTypeCompatibleWith(MediaType.APPLICATION_JSON));
  }

  @SneakyThrows
  @Test
  public void givenInvalidInputDueToInvalidMeasurementTypeInputDistancesThenReturns400() {
    String jsonPayload = """
        {
          "outputUnit": "INVALID",
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
    mvc.perform(post("/api/distance/calculator").content(jsonPayload).contentType(MediaType.APPLICATION_JSON))
        .andExpect(status().is4xxClientError());
  }

}
