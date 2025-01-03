package com.thoughtful.logistics.sorting;

import com.thoughtful.logistics.sorting.service.Dispatcher;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;

public class AutomationFactoryTest {

    private AutomationFactory factory;

    @BeforeEach
    void setup() {
        var dispatcher = new Dispatcher();
        factory = new AutomationFactory(dispatcher);
    }

    @ParameterizedTest
    @CsvSource({
            "90, 90, 150, 1, SPECIAL",
            "100, 100, 100, 1, SPECIAL",
            "100, 1, 100, 1, STANDARD",
            "100, 100, 1, 1, STANDARD",
            "1, 1, 1, 1, STANDARD",
            "1, 1, 150, 20, REJECTED",
            "100, 100, 100, 20, REJECTED"
    })
    void testSortNominalCase(int width, int height, int length, int mass, String expected) {
        Assertions.assertThat(factory.sort(width, height, length, mass)).isEqualTo(expected);
    }

    @ParameterizedTest
    @CsvSource({
            "0, -10, 10, 1",
            "-10, 0, 10, 1",
            "10, 10, -10, 1",
            "10, 10, 1001, 1",
            "10, 1, 10, -1",
            "1, 1, 1, 0",
            "1, 1, 10, -1"
    })
    void testInvalidPackage(int width, int height, int length, int mass) {
        Assertions.assertThatThrownBy(() -> factory.sort(width, height, length, mass));
    }

    @ParameterizedTest
    @CsvSource({
            "90, 90, 150, 1, SPECIAL",
            "100, 100, 100, 1, SPECIAL",
            "100, 1, 100, 1, STANDARD",
            "100, 100, 1, 1, STANDARD",
            "1, 1, 1, 1, STANDARD",
            "1, 1, 150, 20, REJECTED",
            "100, 100, 100, 20, REJECTED"
    })
    void testNaiveSortNominalCase(int width, int height, int length, int mass, String expected) {
        Assertions.assertThat(factory.naiveSort(width, height, length, mass)).isEqualTo(expected);
    }

    @ParameterizedTest
    @CsvSource({
            "0, -10, 10, 1",
            "-10, 0, 10, 1",
            "10, 10, -10, 1",
            "10, 10, 1001, 1",
            "10, 1, 10, -1",
            "1, 1, 1, 0",
            "1, 1, 10, -1"
    })
    void testNaiveInvalidPackage(int width, int height, int length, int mass) {
        Assertions.assertThatThrownBy(() -> factory.naiveSort(width, height, length, mass));
    }

}