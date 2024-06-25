package com.neueda.stockapi.entity;

import jakarta.validation.constraints.Min;

public class FilterRequest {

    @Min(value = (long) 0.01, message = "min cannot be less than $0.01")
    private Double min;

    @Min(value = (long) 0.01, message = "max cannot be less than $0.01")
    private Double max;
}
