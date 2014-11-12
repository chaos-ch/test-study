package com.qunar.hotel.sa.product.price.adapter.kaiyuan.bean;

import java.io.Serializable;

public class AdapterBedType implements Serializable {
    /**
	 * 
	 */
    private static final long serialVersionUID = 4240005189425511883L;
    private String id;
    private String description;

    public AdapterBedType() {
        super();
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

}
