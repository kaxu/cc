package com.cc.classes.controllers;


import com.cc.classes.model.ClassInfo;
import com.cc.classes.services.ClassInfoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.ResponseStatus;

@RestController
@RequestMapping(value="v1/classes")
public class ClassInfoServiceController {
    @Autowired
    private ClassInfoService classInfoService;


    @RequestMapping(value="/{classId}",method = RequestMethod.GET)
    public ClassInfo getClassInfo(@PathVariable("classId") Long classId) {
        return classInfoService.getClassInfo(classId);
    }

    @RequestMapping(value="/{classId}",method = RequestMethod.PUT)
    public void getClassInfo(@PathVariable("classId") Long classId,@RequestBody ClassInfo classInfo) {
        if(!classId.equals(classInfo.getId()))
            return;
        classInfoService.updateClassInfo(classInfo.getId(),classInfo.getName());
    }


}
