package com.portal.utils;

import org.apache.commons.codec.digest.DigestUtils;

public class MD5util {
    public static String md5(String src){
        return DigestUtils.md5Hex(src);
    }


    private static final String salt = "1a2b3c4d";
    //第一次md5
    public static String inputPassMD5(String inputPass){
        String str = ""+salt.charAt(0)+salt.charAt(2)+inputPass+salt.charAt(5)+salt.charAt(4);
        return DigestUtils.md5Hex(str);
    }
    //第二次MD5
    public static String formPassDB(String formPass,String salt){
        String str = ""+salt.charAt(0)+salt.charAt(2)+formPass+salt.charAt(5)+salt.charAt(4);
        return DigestUtils.md5Hex(str);
    }

    //直接存储到数据库
    public static String inputToDB(String input,String saltDB){
        String formPass = inputPassMD5(input);
        return formPassDB(formPass,saltDB);
    }

    public static void main(String[] args) {
        System.out.println(inputPassMD5("123456"));
        System.out.println(formPassDB(inputPassMD5("123456"),"1a2b3c4d"));
        System.out.println(inputToDB("123456","1a2b3c4d"));
    }
}
