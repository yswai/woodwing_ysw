package com.woodwing.test.distancecalc.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class DistanceCalculatorItemDto {

  private BigDecimal distanceValue;
  private DistanceUnit distanceUnit;

}
