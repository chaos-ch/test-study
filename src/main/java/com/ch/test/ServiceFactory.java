package com.ch.test;

import com.google.common.collect.Maps;
import org.springframework.context.ApplicationContext;
import org.springframework.stereotype.Service;

import javax.annotation.PostConstruct;
import javax.annotation.Resource;
import java.util.Map;

/**
 * Created by he.chen on 14-10-23.
 */
@Service
public class ServiceFactory {
    @Resource
    private  ApplicationContext applicationContext;

    private  Map<String,OtaAdapterService> otaServiceMap = Maps.newHashMap();

    public OtaAdapterService getOtaService(String otaKey){
        return otaServiceMap.get(otaKey);
    }
//    public static ApplicationContext getApplicationContext() {
//        return applicationContext;
//    }
    @PostConstruct
    public  void registerSupplierServices() {
//        applicationContext = context;
//        Map<String,Object> map = applicationContext.getBeansWithAnnotation(OTAService.class);
       Map<String,OtaAdapterService> map = applicationContext.getBeansOfType(OtaAdapterService.class);
        for (Map.Entry<String, OtaAdapterService> entry: map.entrySet()) {
            otaServiceMap.put(entry.getKey(), (OtaAdapterService)entry.getValue());
        }
        System.out.println("++++++++++++++++++++++++++++++++++++++");
        System.out.println(otaServiceMap);
    }
}
