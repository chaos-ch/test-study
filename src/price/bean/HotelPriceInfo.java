package com.qunar.hotel.sa.product.price.bean;

public class HotelPriceInfo<T, S> {
    private T first;
    private S second;

    public HotelPriceInfo(T f, S s) {
        first = f;
        second = s;
    }

    public HotelPriceInfo(HotelPriceInfo<T, S> pair) {
        if (pair != null) {
            first = pair.first;
            second = pair.second;
        }
    }

    public HotelPriceInfo() {
        super();
    }

    public T getFirst() {
        return first;
    }

    public S getSecond() {
        return second;
    }

    public HotelPriceInfo<T, S> setFirst(T f) {
        first = f;
        return this;
    }

    public HotelPriceInfo<T, S> setSecond(S s) {
        second = s;
        return this;
    }
}
