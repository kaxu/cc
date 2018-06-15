package com.cc.classes.services;

import com.cc.classes.model.ClassInfo;
import com.cc.classes.repository.ClassInfoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.UUID;

@Service
public class ClassInfoService {
    @Autowired
    private ClassInfoRepository classInfoRepository;

    public ClassInfo getClassInfo(Long classId) {
        return classInfoRepository.findById(classId);
    }


}
