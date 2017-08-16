package com.cl.learnSpring.userManage.service;


import com.cl.learnSpring.userManage.entity.UserPo;

import java.io.UnsupportedEncodingException;
import java.security.NoSuchAlgorithmException;

/**
 * Created by cl on 2017/5/25.
 */
public interface UserService {

    /**
     * 根据邮箱和密码获取用户信息
     * @param username
     * @param password
     * @return
     */
    UserPo login(String username, String password) throws UnsupportedEncodingException, NoSuchAlgorithmException;
    

}
