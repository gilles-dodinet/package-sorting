package com.thoughtful.logistics.sorting;

import com.thoughtful.logistics.sorting.model.Dimensions;
import com.thoughtful.logistics.sorting.model.ShippingPackage;
import com.thoughtful.logistics.sorting.naive.Sorter;
import com.thoughtful.logistics.sorting.service.Dispatcher;

public class AutomationFactory {
    private final Dispatcher dispatcher;

    public AutomationFactory(Dispatcher dispatcher) {
        this.dispatcher = dispatcher;
    }

    public String sort(double width, double height, double length, double mass) {
        var shippingPackage = new ShippingPackage(new Dimensions(width, height, length), mass);
        return dispatcher.dispatch(shippingPackage).name();
    }

    @Deprecated
    public String naiveSort(double width, double height, double length, double mass) {
        return new Sorter().sort(width, height, length, mass);
    }
}
