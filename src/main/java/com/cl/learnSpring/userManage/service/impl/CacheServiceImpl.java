package com.cl.learnSpring.userManage.service.impl;

import com.cl.learnSpring.userManage.dao.UserDao;
import com.cl.learnSpring.userManage.entity.CacheManager;
import com.cl.learnSpring.userManage.entity.UserPo;
import com.cl.learnSpring.userManage.service.CacheService;
import com.cl.learnSpring.userManage.service.UserService;
import com.cl.learnSpring.userManage.util.PasswordHelper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.CacheConfig;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.io.UnsupportedEncodingException;
import java.security.NoSuchAlgorithmException;

/**
 * Created by cl on 2017/8/31.
 */
@Service
@CacheConfig
public class CacheServiceImpl implements CacheService {

    CacheManager<UserPo> cacheManager;

    public CacheServiceImpl() {
        cacheManager = new CacheManager<UserPo>();
    }

    @Resource
    private UserDao userDao;

    @Resource
    UserService userService;


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

    /**
     *
     * @param userName
     * @return
     */
    @Cacheable(cacheNames = "getUserPo")
    public UserPo getUserPo(String userName,String password) throws Exception {
        return getUserPoByName(userName,password);
    }

    UserPo getUserPoByName(String userName,String password) throws Exception{
        UserPo userPo = userService.login(userName,password);
        System.out.println("从缓存中获取");
        return userPo;
    }
}
