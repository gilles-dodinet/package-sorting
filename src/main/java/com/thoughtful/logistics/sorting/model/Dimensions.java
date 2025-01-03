package com.thoughtful.logistics.sorting.model;


import jakarta.validation.constraints.DecimalMax;
import jakarta.validation.constraints.DecimalMin;

/**
 * TODO check with Logistics what the actual minimum and maximum requirements are
 */
public record Dimensions(
     @DecimalMin(value = "0.0", inclusive = false) @DecimalMax(value = "1000.0", inclusive = false) double width,
     @DecimalMin(value = "0.0", inclusive = false) @DecimalMax(value = "1000.0", inclusive = false) double height,
     @DecimalMin(value = "0.0", inclusive = false) @DecimalMax(value = "1000.0", inclusive = false) double length) {

    public double volume() {
        return width * height * length;
    }
}
