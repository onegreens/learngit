package com.cl.learnSpring.userManage.service.impl;

import com.cl.learnSpring.userManage.dao.UserDao;
import com.cl.learnSpring.userManage.entity.UserPo;
import com.cl.learnSpring.userManage.service.UserService;
import com.cl.learnSpring.userManage.util.PasswordHelper;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.Resource;
import java.io.UnsupportedEncodingException;
import java.security.NoSuchAlgorithmException;
import java.util.List;
import java.util.Map;

/**
 * Created by cl on 2017/5/25.
 */
@Service("UserService")
@Transactional
public class UserServiceImpl implements UserService {

    @Resource
    private UserDao userDao;


    @Override
    public UserPo login(String username, String password) throws UnsupportedEncodingException, NoSuchAlgorithmException {
        UserPo userPo = userDao.loginCheck(username, PasswordHelper.encryptPassword(password));
        if(userPo == null){
            return null;
        }else{
            return userPo;
        }
    }
}
