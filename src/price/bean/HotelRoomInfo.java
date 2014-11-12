package com.qunar.hotel.sa.product.price.bean;

public class HotelRoomInfo<T, S> {
    private T first;
    private S second;

    public HotelRoomInfo(T f, S s) {
        first = f;
        second = s;
    }

    public HotelRoomInfo(HotelRoomInfo<T, S> pair) {
        if (pair != null) {
            first = pair.first;
            second = pair.second;
        }
    }

    public T getFirst() {
        return first;
    }

    public S getSecond() {
        return second;
    }

    public HotelRoomInfo<T, S> setFirst(T f) {
        first = f;
        return this;
    }

    public HotelRoomInfo<T, S> setSecond(S s) {
        second = s;
        return this;
    }
}
