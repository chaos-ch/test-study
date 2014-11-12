package com.qunar.hotel.sa.product.price.service;

import java.util.Map;

import javax.annotation.PostConstruct;
import javax.annotation.Resource;

import org.springframework.context.ApplicationContext;
import org.springframework.stereotype.Service;

import com.google.common.collect.Maps;

/**
 * Created by he.chen on 14-10-23.
 */
@Service
public class SupplierProductServiceFactory {
    @Resource
    private  ApplicationContext applicationContext;

    private  Map<String,SupplierProductService> otaServiceMap = Maps.newHashMap();

    public SupplierProductService buildeSupplierService(String otaKey){
        return otaServiceMap.get(otaKey);
    }
//    public static ApplicationContext getApplicationContext() {
//        return applicationContext;
//    }
    @PostConstruct
    public  void registerSupplierServices() {
       Map<String,SupplierProductService> map = applicationContext.getBeansOfType(SupplierProductService.class);
        otaServiceMap = Maps.newHashMap(map);
    }
}
