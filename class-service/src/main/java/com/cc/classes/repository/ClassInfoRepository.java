package com.cc.classes.repository;

import com.cc.classes.model.ClassInfo;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ClassInfoRepository extends CrudRepository<ClassInfo,Long>  {
    public ClassInfo findById(Long classId);
}
