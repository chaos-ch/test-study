package com.qunar.test;

import com.google.common.collect.Lists;
import com.google.common.collect.Maps;

import java.util.List;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.atomic.AtomicInteger;

/**
 * Created by he.chen on 14-7-31.
 */
public class App {
    public static void main(String[] args) {
        DataSource dataSource1 = new DataSource();
        DataSource dataSource2 = new DataSource();
        Class c1 = dataSource1.getClass();
        Class c2 = dataSource2.getClass();
        System.out.println(c1);
        System.out.println(c2);


        List<String> stringList = Lists.newArrayList("hello", "world");
        boolean isContained = stringList.contains(null);
        System.out.println(isContained);
        ServiceFactory serviceFactory = new ServiceFactory();
        ConcurrentHashMap<String, AtomicInteger> map = new ConcurrentHashMap<String, AtomicInteger>();
        AtomicInteger count = map.putIfAbsent("aaa",new AtomicInteger(0));
        if(count == null){
            count = map.putIfAbsent("aaa",new AtomicInteger(0));
        }
        System.out.println(count.incrementAndGet());
        System.out.printf(map.get("aaa").toString());
    }
}
