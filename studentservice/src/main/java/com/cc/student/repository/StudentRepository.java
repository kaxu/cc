package com.cc.student.repository;

import com.cc.student.model.Student;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface StudentRepository extends CrudRepository<Student,String> {

    public List<Student> findByClassInfoId(Long classId);
}
