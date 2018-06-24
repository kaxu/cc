package com.cc.student.repository;

import com.cc.student.model.ClassInfo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.HashOperations;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Repository;

import javax.annotation.PostConstruct;

//@Repository
public class ClassInfoRepositoryImpl implements ClassInfoRedisRepository {

    private static final String HASH_NAME ="classinfo";

    private RedisTemplate<String, ClassInfo> redisTemplate;
    private HashOperations hashOperations;

    public ClassInfoRepositoryImpl(){
        super();
    }

//    @Autowired
    private ClassInfoRepositoryImpl(RedisTemplate redisTemplate) {
        this.redisTemplate = redisTemplate;
    }

//    @PostConstruct
    private void init() {
        hashOperations = redisTemplate.opsForHash();
    }

    @Override
    public ClassInfo findClassInfo(Long classInfoId) {
        return (ClassInfo) hashOperations.get(HASH_NAME, classInfoId);
    }

    @Override
    public void updateClassInfo(ClassInfo classInfo) {
        hashOperations.put(HASH_NAME, classInfo.getId(), classInfo);
    }

    @Override
    public void saveClassInfo(ClassInfo classInfo) {
        hashOperations.put(HASH_NAME, classInfo.getId(), classInfo);
    }

    @Override
    public void deleteClassInfo(Long classInfoId) {
        hashOperations.delete(HASH_NAME, classInfoId);
    }
}
