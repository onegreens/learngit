package com.cl.learnSpring.userManage.dao;

import com.cl.learnSpring.userManage.entity.UserPo;
import org.apache.ibatis.annotations.*;
import org.mybatis.spring.annotation.MapperScan;
import org.springframework.stereotype.Repository;

/**
 * Created by cl on 2017/5/25.
 * 对用户数据的相关操作
 */
@Mapper
public interface UserDao {

    /**
     * 根据账号和密码获取用户信息
     *
     * @param username
     * @param password
     * @return
     */
    UserPo loginCheck(@Param("username") String username, @Param("password") String password);

}
