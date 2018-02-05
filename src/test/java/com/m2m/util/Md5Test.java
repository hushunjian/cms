package com.m2m.util;

import org.junit.Assert;
import org.junit.Test;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
public class Md5Test {
    @Test
    public void testConvert() {
        String md5InHex = MD5.getMD5InHex("s3cret");
        Assert.assertEquals("33e1b232a4e6fa0028a6670753749a17", md5InHex);
    }
}
