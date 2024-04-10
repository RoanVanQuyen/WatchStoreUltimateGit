package com.example.watchstoreultimate.service;

import java.util.List;
import java.util.Map;
import java.util.Set;

public interface RedisService {
    void setTimeToLive(String key , Long time) ;
    void hashSet(String key , String field , Object value) ;
    Object hashGet(String key, String field) ;
    List<Object> hashGetByFieldPrefix(String key, String fieldPrefix) ;
    Set<String> getByFieldPrefix(String key) ;
    boolean hashExist(String key , String field) ;
    void delete() ;
    void hashDel(String key);
    void delete(String key , String field) ;
    void delete(String key , List<String> fields );

}
