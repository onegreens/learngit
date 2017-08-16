package com.cl.learnSpring.userManage.util;

import com.cl.learnSpring.userManage.entity.UserPo;
import sun.misc.BASE64Encoder;

import java.io.UnsupportedEncodingException;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

/**
 * <p>User: Zhang Kaitao
 * <p>Date: 14-1-28
 * <p>Version: 1.0
 */
public class PasswordHelper {

    private static String algorithmName = "md5";
    private static final int hashIterations = 2;

    public static final String encryptPassword(String str) throws NoSuchAlgorithmException, UnsupportedEncodingException {
        // 确定计算方法
        MessageDigest md5 = MessageDigest.getInstance("MD5");
        BASE64Encoder base64en = new BASE64Encoder();
        // 加密后的字符串
        String newstr = base64en.encode(md5.digest(str.getBytes("utf-8")));
        return newstr;
    }

    public static final void encryptPassword(UserPo userPo) throws NoSuchAlgorithmException, UnsupportedEncodingException {
        // 确定计算方法
        MessageDigest md5 = MessageDigest.getInstance("MD5");
        BASE64Encoder base64en = new BASE64Encoder();
        // 加密后的字符串
        String newstr = base64en.encode(md5.digest(userPo.getPswd().getBytes("utf-8")));
        userPo.setPswd(newstr);
    }

    public static void main(String[] args) throws UnsupportedEncodingException, NoSuchAlgorithmException {
        System.out.println(PasswordHelper.encryptPassword("123"));
        System.out.println(PasswordHelper.encryptPassword("123"));
        System.out.println(PasswordHelper.encryptPassword("123"));
        System.out.println(PasswordHelper.encryptPassword("123"));
    }



}
