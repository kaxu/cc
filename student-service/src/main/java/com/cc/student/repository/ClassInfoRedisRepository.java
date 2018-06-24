package com.cc.student.repository;

import com.cc.student.model.ClassInfo;

public interface ClassInfoRedisRepository {

    ClassInfo findClassInfo(Long classInfoId);

    void updateClassInfo(ClassInfo classInfo);

    void saveClassInfo(ClassInfo classInfo);

    void deleteClassInfo(Long classInfoId);
}
