package com.bjpowernode;

import com.bjpowernode.utils.MD5Util;
import org.junit.Test;

public class test {
    @Test
    public void testMD5(){
        String mi= MD5Util.getMD5("123456");
        System.out.println(mi);
    }
}
