package com.cc.student.services;

import com.cc.student.clients.ClassInfoFeignClient;
import com.cc.student.converter.StudentToVoConverter;
import com.cc.student.model.ClassInfo;
import com.cc.student.model.Student;
import com.cc.student.repository.StudentRepository;
import com.cc.student.vo.StudentVo;
import com.netflix.discovery.converters.Auto;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class StudentService {

    @Autowired
    private StudentRepository studentRepository;

    @Autowired
    private StudentToVoConverter studentToVoConverter;

    @Autowired
    private ClassInfoFeignClient classInfoFeignClient;

    public List<Student> findStudentsByClassId(Long classId){
        return studentRepository.findByClassInfoId(classId);
    }

    public StudentVo findStudentById(Long classId,Long studentId){
        List<Student> students = findStudentsByClassId(classId);
        Student student = null;
        for(Student student1:students){
            if(student1.getId().equals(studentId))
                student = student1;
        }
        StudentVo studentVo = studentToVoConverter.convert(student);
        ClassInfo classInfo = classInfoFeignClient.getClassInfo(classId);
        studentVo.setClassName(classInfo.getName());
        return studentVo;
    }

}
