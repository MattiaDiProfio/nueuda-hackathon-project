package com.neueda.stockapi.entity;

import jakarta.validation.constraints.Min;

import lombok.Data;
import lombok.NonNull;

@Data
public class FilterRequest {

    @NonNull
    @Min(value = (long) 0.01, message = "min cannot be less than $0.01")
    private Double min;

    @NonNull
    @Min(value = (long) 0.01, message = "max cannot be less than $0.01")
    private Double max;
}
