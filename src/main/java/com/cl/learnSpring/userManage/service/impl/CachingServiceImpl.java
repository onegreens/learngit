package com.cl.learnSpring.userManage.service.impl;

import com.cl.learnSpring.userManage.entity.MemberPo;
import com.cl.learnSpring.userManage.entity.UserPo;
import com.cl.learnSpring.userManage.entity.VisitorPo;
import org.springframework.cache.annotation.CacheConfig;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.cache.annotation.Caching;

import java.util.HashMap;
import java.util.Map;

/**
 * Created by cl on 2017/9/1.
 * Caching 缓存组的测试
 *
 * CacheConfig 全局缓存
 */
@CacheConfig(cacheNames = "CachingServiceImpl")
public class CachingServiceImpl {
    private Map<Integer, UserPo> ppl = new HashMap<Integer, UserPo>();

    {
        ppl.put(1, new MemberPo(1, "q1"));
        ppl.put(2, new VisitorPo(2, "q2"));
    }

    @Caching(cacheable = {
            @Cacheable(value = "members",
                    condition = "@userPo instanceof T(com.cl.learnSpring.userManage.entity.MemberPo) "),
            @Cacheable(value = "visitors",
                    condition = "@userPo instanceof T(com.cl.learnSpring.userManage.entity.VisitorPo) ")
    })
    public UserPo getUser(UserPo userPo) {
        return ppl.get(userPo.getId());
    }
}
