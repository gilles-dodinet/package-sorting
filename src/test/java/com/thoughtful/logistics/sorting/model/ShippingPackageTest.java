package com.thoughtful.logistics.sorting.model;

import org.assertj.core.api.Assertions;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;

import java.util.stream.Stream;

public class ShippingPackageTest {

    @ParameterizedTest
    @MethodSource("packages")
    void testBulkyOrHeavyPackages(ShippingPackage shippingPackage, boolean bulky, boolean heavy) {
        Assertions.assertThat(shippingPackage.isBulky()).isEqualTo(bulky);
        Assertions.assertThat(shippingPackage.isHeavy()).isEqualTo(heavy);
    }


    static Stream<Arguments> packages() {
        return Stream.of(
                Arguments.of(new ShippingPackage(new Dimensions(90, 90, 150), 1), true, false),
                Arguments.of(new ShippingPackage(new Dimensions(100, 100, 100), 1), true, false),
                Arguments.of(new ShippingPackage(new Dimensions(100, 1, 100), 1), false, false),
                Arguments.of(new ShippingPackage(new Dimensions(100, 100, 1), 1), false, false),
                Arguments.of(new ShippingPackage(new Dimensions(1, 1, 1), 1), false, false),
                Arguments.of(new ShippingPackage(new Dimensions(1, 1, 150), 20), true, true)

        );
    }

}
