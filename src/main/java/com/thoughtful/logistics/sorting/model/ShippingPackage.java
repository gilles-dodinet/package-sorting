package com.thoughtful.logistics.sorting.model;


import jakarta.validation.Valid;
import jakarta.validation.constraints.DecimalMax;
import jakarta.validation.constraints.DecimalMin;

public record ShippingPackage(@Valid Dimensions dimensions,
                              @DecimalMin(value = "0.0", inclusive = false) @DecimalMax(value = "150.0", inclusive = false)  double weight) {
    private static final int MAX_VOLUME = 1_000_000;
    private static final int MAX_DIMENSION = 150;
    private static final int MAX_WEIGHT = 20;

    public boolean isBulky() {
        return dimensions.volume() >= MAX_VOLUME || hasLongSide();
    }

    public boolean isHeavy() {
        return weight >= MAX_WEIGHT;
    }

    private boolean hasLongSide() {
        return dimensions.width() >= MAX_DIMENSION ||
                dimensions.height() >= MAX_DIMENSION ||
                dimensions.length() >= MAX_DIMENSION;
    }

}
