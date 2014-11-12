package com.qunar.hotel.sa.product.price.bean;


/**
 * Bean 转换模板
 * @author jianjun.li created on 6/6/14 12:04 PM
 * @version $Id$
 */
public interface Adaptor<F, T> {

    public T adaptorFrom(F bean) throws Exception;
}
