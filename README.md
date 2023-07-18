# Distance Calculator 
## Feature
A web service that accepts two distances (numbers) and returns the total distance (sum of
both)

## Design
The implementation allows configurable distance conversion mapping to be configured in yaml config file as shown below:
```
conversion:
  mapping:
    METER:
      METER: 1.0
      YARD: 1.09361
    YARD:
      METER: 0.9144
      YARD: 1.0
```

## Getting Started
1. JDK 17, Maven
2. After compilation successful, swagger-ui is available at ```http://localhost:8080/swagger-ui/index.html#/distance-calculator-controller/calculateDistances``` accessible via web browser

