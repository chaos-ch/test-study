package com.qunar.hotel.sa.product.price.adapter.kaiyuan.service;

import com.qunar.hotel.oas.bean.AdapterSupplier;
import com.qunar.hotel.oas.bean.price.SupplierInfo;
import com.qunar.hotel.sa.product.price.service.IOtaPriceInterface;
import org.springframework.context.ApplicationContext;

import javax.annotation.Resource;

public class OtaFactroy {

    @Resource
    private static ApplicationContext applicationContext;

    public static IOtaPriceInterface newPriceRealization(String otaType) {
        StringBuilder realizationName = new StringBuilder(otaType);
        realizationName.append("PriceRealization");
        return (IOtaPriceInterface) applicationContext.getBean(realizationName.toString());
    }

    public static IOtaInterface newRealization(SupplierInfo supplier) {
        IOtaInterface newRealization = newRealization(supplier.getOtaType());
        newRealization.setOtaInfo(supplier);
        return newRealization;
    }
    
    public static IOtaInterface newRealization(String otaType) {
        StringBuilder realizationName = new StringBuilder(otaType);
        realizationName.append("Realization");
        return (IOtaInterface) applicationContext.getBean(realizationName.toString());
    }
    
    
//    public static IUpdateHotelInfoService getUpdateHotelInfoService(com.qunar.hotel.oas.bean.InterfaceType interfaceType) {
//        DataSourceType dataSourceType = DataSourceType.getDasourceTypeBySource(interfaceType);
//        return (IUpdateHotelInfoService) applicationContext.getBean(dataSourceType.getUpdateHotelInfoService());
//    }

//    public static ISupplierInsurance newInsuranceRealization(AdapterSupplier supplier) {
//        ISupplierInsurance supplierInsurance = (ISupplierInsurance) AppContext.getSpringContext().getBean(supplier.getOtaType() + "InsuranceRealization");
//        return supplierInsurance;
//    }

}
