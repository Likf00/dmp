package com.javaTest;

import com.utils.RedisUtil;

/**
 * @author yimting
 * @version 1.0.0
 * @ClassName JedisTest.java
 * @Description test
 * @createTime 2019年05月05日 18:00:00
 */
public class JedisTest {
    public static void main(String[] args) {
        RedisUtil ru = new RedisUtil("192.168.168.14",10000);

        ru.getset("","");

    }




}
