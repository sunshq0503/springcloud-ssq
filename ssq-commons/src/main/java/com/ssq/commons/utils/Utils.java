package com.ssq.commons.utils;

import sun.misc.BASE64Encoder;

import javax.servlet.http.HttpServletRequest;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.Random;
import java.util.UUID;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class Utils {

    /**
     * 判断是否是Email
     * @return
     */
    public static boolean isEmail(String email) {
        Pattern emailPattern = Pattern.compile("\\w+([-+.]\\w+)*@\\w+([-.]\\w+)*\\.\\w+([-.]\\w+)*");
        Matcher matcher = emailPattern.matcher(email);
        if(matcher.find()){
            return true;
        }
        return false;
    }

    private static final String BASE_CODE = "0123456789abcdefghijklmnopqrstuvwxyzABCDEFGHIJKLMNOPQRSTUVWXYZ";
    public static String createVerifyCode(int length){
        String result = "";
        Random random = new Random();
        for (int i = 0; i < length; i++) {
            int index = random.nextInt(BASE_CODE.length());
            char c = BASE_CODE.charAt(index);
            result += c;
        }
        return result;
    }

    /**
     *
     * @return
     */
    public static String createUUID() {
        return UUID.randomUUID().toString().replaceAll("-", "");
    }

    /**
     *
     * @return
     */
    public static String createToken() {
        String token = UUID.randomUUID().toString().replaceAll("-", "");
        // 数据指纹 128位长 16个字节 md5
        try {
            MessageDigest md = MessageDigest.getInstance("md5");
            byte md5[] = md.digest(token.getBytes());
            // base64编码--任意二进制编码明文字符 adfsdfsdfsf
            BASE64Encoder encoder = new BASE64Encoder();
            return encoder.encode(md5);
        } catch (NoSuchAlgorithmException e) {
            throw new RuntimeException(e);
        }
    }

    public static String getIp(HttpServletRequest request){
         String ip=request.getHeader("x-forwarded-for");
               if(ip==null || ip.length()==0 || "unknown".equalsIgnoreCase(ip)){
                     ip=request.getHeader("Proxy-Client-IP");
                  }
              if(ip==null || ip.length()==0 || "unknown".equalsIgnoreCase(ip)){
                   ip=request.getHeader("WL-Proxy-Client-IP");
                 }
             if(ip==null || ip.length()==0 || "unknown".equalsIgnoreCase(ip)){
                     ip=request.getRemoteAddr();
             }

            return ip;
    }
}
