package com.cc.student.controllers;

import com.cc.student.model.Student;
import com.cc.student.services.StudentService;
import com.cc.student.vo.StudentVo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping(value="v1/classes/{classId}/students")
public class StudentServiceController {

    @Autowired
    private StudentService studentService;

    @RequestMapping(value="/",method = RequestMethod.GET)
    public List<Student> getLicenses(@PathVariable("classId") Long classId) {

        return studentService.findStudentsByClassId(classId);
    }

    @RequestMapping(value="/{studentId}",method = RequestMethod.GET)
    public StudentVo getLicenses(@PathVariable("classId") Long classId, @PathVariable("studentId") Long studentId) {

        return studentService.findStudentById(classId,studentId);
    }
}
