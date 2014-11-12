package com.qunar.hotel.sa.product.price.bean;

import com.qunar.hotel.oas.bean.PriceProcessResult;

public interface RoomProcess {

    PriceProcessResult check();

    String getMessage();

    void process();
}
