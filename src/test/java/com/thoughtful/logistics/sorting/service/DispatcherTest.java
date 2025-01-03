package com.thoughtful.logistics.sorting.service;

import com.thoughtful.logistics.sorting.model.Dimensions;
import com.thoughtful.logistics.sorting.model.ShippingPackage;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;

import java.util.stream.Stream;

public class DispatcherTest {

    private Dispatcher dispatcher;

    @BeforeEach
    void setup() {
        dispatcher = new Dispatcher();
    }

    @ParameterizedTest
    @MethodSource("testDispatchArguments")
    void testDispatch(ShippingPackage shippingPackage, ShippingStack stack) {
        Assertions.assertThat(dispatcher.dispatch(shippingPackage)).isEqualTo(stack);
    }

    @ParameterizedTest
    @MethodSource("invalidPackages")
    void testInvalidPackage(ShippingPackage shippingPackage) {
        Assertions.assertThatThrownBy(() -> dispatcher.dispatch(shippingPackage));
    }

    static Stream<Arguments> invalidPackages() {
        return Stream.of(
                Arguments.of(new ShippingPackage(new Dimensions(0, -10, 10), 1)),
                Arguments.of(new ShippingPackage(new Dimensions(-10, 0, 10), 1)),
                Arguments.of(new ShippingPackage(new Dimensions(10, 10, -10), 1)),
                Arguments.of(new ShippingPackage(new Dimensions(10, 10, 1001), 1)),
                Arguments.of(new ShippingPackage(new Dimensions(10, 1, 10), -1)),
                Arguments.of(new ShippingPackage(new Dimensions(1, 1, 1), 0)),
                Arguments.of(new ShippingPackage(new Dimensions(1, 1, 10), -1)),
                Arguments.of(new ShippingPackage(null, 5))
        );
    }
    static Stream<Arguments> testDispatchArguments() {
        return Stream.of(
                Arguments.of(new ShippingPackage(new Dimensions(90, 90, 150), 1), ShippingStack.SPECIAL),
                Arguments.of(new ShippingPackage(new Dimensions(100, 100, 100), 1), ShippingStack.SPECIAL),
                Arguments.of(new ShippingPackage(new Dimensions(100, 1, 100), 1), ShippingStack.STANDARD),
                Arguments.of(new ShippingPackage(new Dimensions(100, 100, 1), 1), ShippingStack.STANDARD),
                Arguments.of(new ShippingPackage(new Dimensions(1, 1, 1), 1), ShippingStack.STANDARD),
                Arguments.of(new ShippingPackage(new Dimensions(1, 1, 150), 20), ShippingStack.REJECTED),
                Arguments.of(new ShippingPackage(new Dimensions(100, 100, 100), 20), ShippingStack.REJECTED)

        );
    }
}
