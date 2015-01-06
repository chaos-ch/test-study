/*
 * Copyright (c) 2013 Qunar.com. All Rights Reserved.
 */
package com.ch.test.gson;

import java.io.Serializable;

/**
 * @author rongqian.xu created on 3/13/14 9:48 PM
 * @version $Id$
 */
public class MobPrefInfo implements Serializable {
    private static final long serialVersionUID = -3690355031114912407L;
    private String name;
    private String desc;

    public String getDesc() {
        return desc;
    }

    public void setDesc(String desc) {
        this.desc = desc;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}
