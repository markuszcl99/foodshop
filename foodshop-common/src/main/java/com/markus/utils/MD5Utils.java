package com.markus.utils;

import org.apache.commons.codec.binary.Base64;

import java.security.MessageDigest;

/**
 * @author: markus
 * @date: 2022/10/7 5:28 PM
 * @Description: 加密工具
 * @Blog: http://markuszhang.com
 * It's my honor to share what I've learned with you!
 */
public class MD5Utils {
    /**
     *
     * @Title: MD5Utils.java
     * @Package com.imooc.utils
     * @Description: 对字符串进行md5加密
     */
    public static String getMD5Str(String strValue) throws Exception {
        MessageDigest md5 = MessageDigest.getInstance("MD5");
        String newstr = Base64.encodeBase64String(md5.digest(strValue.getBytes()));
        return newstr;
    }

    public static void main(String[] args) {
        try {
            String md5 = getMD5Str("markus");
            System.out.println(md5);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
