package com.qunar.hotel.sa.product.price.service;

import com.qunar.hotel.sa.product.price.bean.ProductInfo;
import com.qunar.hotel.sa.product.price.bean.ProductQuery;

/**
 * Created by he.chen on 14-10-27.
 */
public interface SupplierProductService {
    /**
     * 订单报价
     *
     * @param productQuery
     * @return
     * @throws Exception
     */
    public ProductInfo queryOrderProduct(ProductQuery productQuery) throws Exception;

    /**
     * wrapper报价
     *
     * @param productQuery
     * @return
     * @throws Exception
     */
    public ProductInfo queryWrapperProduct(ProductQuery productQuery) throws Exception;
}
