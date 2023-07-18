package com.woodwing.test.distancecalc.dto;

import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class DistanceCalculatorDto {

  @NotNull
  private DistanceUnit outputUnit;

  @NotNull
  @NotEmpty
  @Size(min = 1, max = 2)
  private List<DistanceCalculatorItemDto> inputDistances;

}
