package com.ch.test.json;

import com.ch.test.http.HttpUtil;

/**
 * @author he.chen created on 14-12-23.
 * @version $Id$
 */
public class TestParseJson {
    public static void main(String[] args) {
        try {
            String body = HttpUtil.getContent("http://l-qss1.ops.cn1.qunar.com:9000/api/wrapper_cs_info?product_line_id=2");


            System.out.println(body);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
