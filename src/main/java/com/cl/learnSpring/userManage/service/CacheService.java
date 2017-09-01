package com.cl.learnSpring.userManage.service;

import com.cl.learnSpring.userManage.entity.UserPo;

import java.io.UnsupportedEncodingException;
import java.security.NoSuchAlgorithmException;

/**
 * Created by cl on 2017/8/31.
 */
public interface CacheService {

    /**
     * 根据邮箱和密码获取用户信息
     *
     * @param username
     * @param password
     * @return
     */
    UserPo login(String username, String password) throws UnsupportedEncodingException, NoSuchAlgorithmException;

    /**
     * 根据邮箱和密码获取用户信息
     *
     * @return
     */
    UserPo getUserPo(String userName,String password) throws Exception;

}
