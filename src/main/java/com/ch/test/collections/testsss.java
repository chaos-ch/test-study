package com.ch.test.collections;

import com.google.common.collect.Lists;

import java.util.List;

/**
 * @author he.chen created on 14-12-24.
 * @version $Id$
 */
public class testsss {
    public static void main(String[] args) {
        List<String> list = Lists.newArrayList("123","456","789");
        list.remove("456");
        System.out.println(list);
        list.add(0,"456");
        System.out.println(list);
    }
}
