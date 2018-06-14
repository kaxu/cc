package com.cc.student.services;

import com.cc.student.model.Student;
import com.cc.student.repository.StudentRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class StudentService {

    @Autowired
    private StudentRepository studentRepository;

    public List<Student> findStudentsByClassId(Long classId){
        return studentRepository.findByClassInfoId(classId);
    }



}
