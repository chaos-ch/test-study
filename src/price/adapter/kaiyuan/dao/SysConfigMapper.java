package com.qunar.hotel.sa.product.price.adapter.kaiyuan.dao;

import java.util.List;

import org.springframework.stereotype.Repository;

import com.qunar.hotel.oas.bean.SysConfig;

@Repository
public interface SysConfigMapper {
    public List<SysConfig> getAllSysConfig();

    public void addSysConfig(SysConfig sysConfig);
}
