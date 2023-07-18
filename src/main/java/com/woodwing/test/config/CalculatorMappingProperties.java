package com.woodwing.test.config;

import com.woodwing.test.distancecalc.dto.DistanceUnit;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Configuration;

import java.math.BigDecimal;
import java.util.Map;

@Configuration
@ConfigurationProperties("conversion")
@Data
@NoArgsConstructor
@AllArgsConstructor
public class CalculatorMappingProperties {

  private Map<DistanceUnit, Map<DistanceUnit, BigDecimal>> mapping;

}