/**
 * Copyright &copy; 2012-2016 <a href="https://github.com/thinkgem/jeesite">JeeSite</a> All rights
 * reserved.
 */

package com.thinkgem.jeesite.common.utils;

import java.security.SecureRandom;
import java.util.UUID;

/**
 * 封装各种生成唯一性ID算法的工具类.
 *
 * @author ThinkGem
 * @version 2013 -01-15
 */
// @Service
// @Lazy(false)
public class IdGen /* implements SessionIdGenerator */ { // IdGenerator,

    private static SecureRandom random = new SecureRandom();

    /**
     * 封装JDK自带的UUID, 通过Random数字生成, 中间无-分割.
     *
     * @return the string
     */
    public static String uuid() {
        return UUID.randomUUID().toString().replaceAll("-", "");
    }

    /**
     * 使用SecureRandom随机生成Long.
     *
     * @return the long
     */
    public static long randomLong() {
        return Math.abs(random.nextLong());
    }

    /**
     * The entry point of application.
     *
     * @param args the input arguments
     */
    public static void main(String[] args) {
        System.out.println(IdGen.uuid());
        System.out.println(IdGen.uuid().length());
        // // System.out.println(new IdGen().getNextId());
        // for (int i=0; i<1000; i++){
        // System.out.println(IdGen.randomLong() + " " + IdGen.randomBase62(5));
        // }
    }

}
