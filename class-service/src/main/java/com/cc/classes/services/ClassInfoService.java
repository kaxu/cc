package com.cc.classes.services;

import com.cc.classes.events.source.SimpleSourceBean;
import com.cc.classes.model.ClassInfo;
import com.cc.classes.repository.ClassInfoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.UUID;

@Service
public class ClassInfoService {
    @Autowired
    private ClassInfoRepository classInfoRepository;

    @Autowired
    private SimpleSourceBean simpleSourceBean;

    public ClassInfo getClassInfo(Long classId) {
        return classInfoRepository.findById(classId);
    }

    public void updateClassInfo(Long classId,String name){
        ClassInfo classInfo = classInfoRepository.findById(classId);
        classInfo.setName(name);
        classInfoRepository.save(classInfo);
        simpleSourceBean.publishOrgChange("UPDATE",classId);
    }


}
