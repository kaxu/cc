package com.cc.classes.services;

import com.cc.classes.events.source.SimpleSourceBean;
import com.cc.classes.model.ClassInfo;
import com.cc.classes.repository.ClassInfoRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.UUID;

@Service
public class ClassInfoService {

    private static final Logger logger = LoggerFactory.getLogger(ClassInfoService.class);

    @Autowired
    private ClassInfoRepository classInfoRepository;

    @Autowired
    private SimpleSourceBean simpleSourceBean;

    public ClassInfo getClassInfo(Long classId) {
        logger.debug("ClassInfoService.getClassInfo {}",classId);
        return classInfoRepository.findById(classId);
    }

    public void updateClassInfo(Long classId,String name){
        ClassInfo classInfo = classInfoRepository.findById(classId);
        classInfo.setName(name);
        classInfoRepository.save(classInfo);
        simpleSourceBean.publishOrgChange("UPDATE",classId);
    }


}
