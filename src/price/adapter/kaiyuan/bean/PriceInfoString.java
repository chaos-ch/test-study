package com.qunar.hotel.sa.product.price.adapter.kaiyuan.bean;

import java.io.Serializable;


/**
 * @Date:Jun 6, 2014
 * @Time:12:28:07 PM
 * @Author:fengtao.yang
 */
public class PriceInfoString implements Serializable{
    /**
     * 
     */
    private static final long serialVersionUID = -6659883674626526979L;
    private ParseType parseType;
    private String initialString;

    public PriceInfoString() {
    }

    public PriceInfoString(ParseType parseType, String initialString) {
        this.parseType = parseType;
        this.initialString = initialString;
    }

    public ParseType getParseType() {
        return parseType;
    }

    public String getInitialString() {
        return initialString;
    }
    

    public void setParseType(ParseType parseType) {
        this.parseType = parseType;
    }

    public void setInitialString(String initialString) {
        this.initialString = initialString;
    }



    public static enum ParseType {
        RP("解析ratePlan"), RS("解析房态");
        private String desc;

        ParseType(String desc) {
            this.desc = desc;
        }

        public String getDesc() {
            return desc;
        }

    }
}
