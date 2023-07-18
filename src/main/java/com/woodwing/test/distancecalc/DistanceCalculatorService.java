package com.woodwing.test.distancecalc;

import com.woodwing.test.config.CalculatorMappingProperties;
import com.woodwing.test.distancecalc.dto.DistanceCalculatorDto;
import com.woodwing.test.distancecalc.dto.DistanceCalculatorItemDto;
import com.woodwing.test.distancecalc.dto.DistanceUnit;
import jakarta.annotation.PostConstruct;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.util.Map;

@Service
public class DistanceCalculatorService {

  private Map<DistanceUnit, Map<DistanceUnit, BigDecimal>> mapping;

  @Autowired
  private CalculatorMappingProperties mappingProperties;

  @PostConstruct
  public void init() {
    this.mapping = mappingProperties.getMapping();
  }

  public DistanceCalculatorItemDto calculateDistances(DistanceCalculatorDto distanceCalculatorDto) {
    BigDecimal total = BigDecimal.ZERO;
    for (DistanceCalculatorItemDto item : distanceCalculatorDto.getInputDistances()) {
      // Normalize to METER
      BigDecimal meterConversionRatio = mapping.get(item.getDistanceUnit()).get(DistanceUnit.METER);
      total = total.add(meterConversionRatio.multiply(item.getDistanceValue()));
    }
    // Convert to final output unit
    BigDecimal outputConversionRatio = mapping.get(DistanceUnit.METER).get(distanceCalculatorDto.getOutputUnit());
    BigDecimal totalConverted = outputConversionRatio.multiply(total);
    return new DistanceCalculatorItemDto(totalConverted, distanceCalculatorDto.getOutputUnit());
  }

}
