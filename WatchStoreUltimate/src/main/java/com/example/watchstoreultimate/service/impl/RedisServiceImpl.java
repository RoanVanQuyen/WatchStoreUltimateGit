package com.example.watchstoreultimate.service.impl;

import com.example.watchstoreultimate.service.RedisService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.HashOperations;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.concurrent.TimeUnit;

@Service
public class RedisServiceImpl implements RedisService {
    RedisTemplate<String , Object> redisTemplate ;
    HashOperations<String , String , Object> hashOperations ;

    @Autowired
    public RedisServiceImpl(RedisTemplate<String, Object> redisTemplate){
        this.redisTemplate = redisTemplate;
        this.hashOperations = redisTemplate.opsForHash();
    }

    @Override
    public void setTimeToLive(String key, Long time) {
        this.redisTemplate.expire(key , time , TimeUnit.MINUTES) ;
    }

    @Override
    public void hashSet(String key, String field, Object value) {
        hashOperations.put(key ,field , value);
    }

    @Override
    public Object hashGet(String key, String field) {
        return hashOperations.get(key , field);
    }

    @Override
    public void hashDel(String key) {
        Set<String> set = hashOperations.entries(key).keySet() ;
        for(String x : set){
            hashOperations.delete(key , x)  ;
        }
    }

    @Override
    public List<Object> hashGetByFieldPrefix(String key, String fieldPrefix) {
        List<Object> objects = new ArrayList<>() ;
        Map<String , Object> map = hashOperations.entries(key);
        for(String x : map.keySet()){
            if(x.startsWith(fieldPrefix)){
                objects.add(map.get(x)) ;
            }
        }
        return objects;
    }

    @Override
    public Set<String> getByFieldPrefix(String key) {
        return hashOperations.entries(key).keySet() ;
    }

    @Override
    public boolean hashExist(String key, String field) {
        return hashOperations.hasKey(key, field);
    }

    @Override
    public void delete() {
        redisTemplate.getConnectionFactory().getConnection().flushAll();
    }

    @Override
    public void delete(String key, String field) {
        hashOperations.delete(key , field) ;
    }

    @Override
    public void delete(String key, List<String> fields) {
        for(String x : fields){
            hashOperations.delete(key,x) ;
        }
    }
}
