package com.thoughtful.logistics.sorting.naive;


public class Sorter {
    public String sort(double width, double height, double length, double mass) {
        if ( width <= 0 || width >= 1000 ||
                height <= 0 || height >= 1000 ||
                length <= 0 || length >= 1000 ||
                mass <= 0 || mass >= 150) {
            throw new RuntimeException("invalid package");
        }

        var bulky = ( width * height * length ) >= 1_000_000 ||
                width >= 150 || height >= 150 || length >= 150;

        var heavy = mass >= 20;

        if ( bulky && heavy ) {
            return "REJECTED";
        }

        if ( bulky || heavy ) {
            return "SPECIAL";
        }

        return "STANDARD";
    }
}
