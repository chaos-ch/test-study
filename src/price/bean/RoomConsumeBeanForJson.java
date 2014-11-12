package com.qunar.hotel.sa.product.price.bean;

import java.io.Serializable;

public class RoomConsumeBeanForJson implements Serializable {

    /**
	 * 
	 */
    private static final long serialVersionUID = -8758195838163312788L;
    private String fromDate;
    private String toDate;
    private int count;

    public RoomConsumeBeanForJson() {
        super();
    }

    public String getFromDate() {
        return fromDate;
    }

    public void setFromDate(String fromDate) {
        this.fromDate = fromDate;
    }

    public String getToDate() {
        return toDate;
    }

    public void setToDate(String toDate) {
        this.toDate = toDate;
    }

    public int getCount() {
        return count;
    }

    public void setCount(int count) {
        this.count = count;
    }

}
