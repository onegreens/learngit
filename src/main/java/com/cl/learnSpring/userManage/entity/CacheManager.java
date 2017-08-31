package com.cl.learnSpring.userManage.entity;

import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

/**
 * Created by cl on 2017/8/31.
 * 创建一个缓存管理器
 */
public class CacheManager<T> {
    private Map<String,T> cache = new ConcurrentHashMap<String, T>();

    public T getValue(String key){
        return cache.get(key);
    }

    public void addOrUpdateCache(String key,T value){
        cache.put(key,value);
    }

    public void evictCache(String key){
        if (cache.containsKey(key)){
            cache.remove(key);
        }
    }

    public void evictCache(){
        cache.clear();
    }
}
