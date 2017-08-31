package com.cl.learnSpring.userManage.service.impl;

import com.cl.learnSpring.userManage.dao.UserDao;
import com.cl.learnSpring.userManage.entity.CacheManager;
import com.cl.learnSpring.userManage.entity.UserPo;
import com.cl.learnSpring.userManage.service.CacheService;
import com.cl.learnSpring.userManage.util.PasswordHelper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.io.UnsupportedEncodingException;
import java.security.NoSuchAlgorithmException;

/**
 * Created by cl on 2017/8/31.
 */
@Service
public class CacheServiceImpl implements CacheService {

    CacheManager<UserPo> cacheManager;

    public CacheServiceImpl() {
        cacheManager = new CacheManager<UserPo>();
    }

    @Resource
    private UserDao userDao;


    @Override
    public UserPo login(String username, String password) throws UnsupportedEncodingException, NoSuchAlgorithmException {
        UserPo userPo = cacheManager.getValue(username);
        if (userPo != null) {
            String psd = userPo.getPswd();
            if (psd.equals(PasswordHelper.encryptPassword(password))) {
                return userPo;
            } else {
                userPo = userDao.loginCheck(username, PasswordHelper.encryptPassword(password));
                if (userPo == null) {
                    return null;
                } else {
                    cacheManager.addOrUpdateCache(username, userPo);
                    return userPo;
                }
            }
        } else {
            userPo = userDao.loginCheck(username, PasswordHelper.encryptPassword(password));
            if (userPo == null) {
                return null;
            } else {
                cacheManager.addOrUpdateCache(username, userPo);
                return userPo;
            }

        }

    }
}
