package com.woodwing.test.distancecalc;

import com.woodwing.test.distancecalc.dto.DistanceCalculatorDto;
import com.woodwing.test.distancecalc.dto.DistanceCalculatorItemDto;
import jakarta.validation.Valid;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@Slf4j
@RestController
public class DistanceCalculatorController {

  @Autowired
  private DistanceCalculatorService distanceCalculatorService;

  @PostMapping("/api/distance/calculator")
  public ResponseEntity<DistanceCalculatorItemDto> calculateDistances(@Valid @RequestBody DistanceCalculatorDto distanceCalculatorDto) {
    return ResponseEntity.status(HttpStatus.OK).body(distanceCalculatorService.calculateDistances(distanceCalculatorDto));
  }

}
