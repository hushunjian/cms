package com.m2m.util;

import org.apache.shiro.crypto.hash.Md5Hash;

public class MD5 {
    public static String getMD5InHex(String input) {
        return new Md5Hash(input).toHex();
    }

    public static String getMD5InHex(String input, String salt) {
        return new Md5Hash(input, salt).toHex();
    }
}