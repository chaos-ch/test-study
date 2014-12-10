package com.qunar.test.spring.controller;


import com.google.common.collect.Maps;
import com.qunar.hotel.qta.order.core.api.bean.enums.SourceType;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.Map;

/**
 * Created by he.chen on 14-12-1.
 */
@Controller
public class Test {
    @ResponseBody
    @RequestMapping("/spring/controller/test")
    public Object getTestData(String userName,@RequestParam("age")Integer age){
        System.out.println("=================================");
        System.out.println(userName);
        Map<String,Object> map = Maps.newHashMap();
        map.put("userName",userName);
        map.put("age",age);
        return map;
    }
}
