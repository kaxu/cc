package com.cc.student.services;

import com.cc.student.clients.ClassInfoFeignClient;
import com.cc.student.clients.ClassInfoRestTemplateClient;
import com.cc.student.converter.StudentToVoConverter;
import com.cc.student.model.ClassInfo;
import com.cc.student.model.Student;
import com.cc.student.repository.StudentRepository;
import com.cc.student.vo.StudentVo;
import com.netflix.hystrix.contrib.javanica.annotation.HystrixCommand;
import com.netflix.hystrix.contrib.javanica.annotation.HystrixProperty;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.cc.student.utils.UserContextHolder;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

@Service
public class StudentService {

    private static final Logger logger = LoggerFactory.getLogger(StudentService.class);

    @Autowired
    private StudentRepository studentRepository;

    @Autowired
    private StudentToVoConverter studentToVoConverter;

    @Autowired
    private ClassInfoFeignClient classInfoFeignClient;

    @Autowired
    private ClassInfoRestTemplateClient classInfoRestTemplateClient;

    @HystrixCommand(
//            fallbackMethod = "buildFallbackStudents",
            threadPoolKey = "StudentsByClassIdThreadPool",
            threadPoolProperties = {
                    @HystrixProperty(name="coreSize",value = "30"),
                    @HystrixProperty(name="maxQueueSize",value = "10")
            },
            commandProperties ={
                    @HystrixProperty(name="circuitBreaker.requestVolumeThreshold",value="10"),
                    @HystrixProperty(name="circuitBreaker.errorThresholdPercentage", value="75"),
                    @HystrixProperty(name="circuitBreaker.sleepWindowInMilliseconds", value="7000"),
                    @HystrixProperty(name="metrics.rollingStats.timeInMilliseconds", value="15000"),
                    @HystrixProperty(name="metrics.rollingStats.numBuckets", value="5")
            }
    )
    public List<Student> findStudentsByClassId(Long classId){
        logger.debug("StudentService.findStudentsByClassId  Correlation id: {}", UserContextHolder.getContext().getCorrelationId());

//        randomlyRunLong();

        return studentRepository.findByClassInfoId(classId);
    }

    private List<Student> buildFallbackStudents(Long classId){
        List<Student> students =new ArrayList<Student>();
        Student student = new Student();
        student.setName("Fallback");
        student.setClassInfoId(classId);
        student.setId(0L);
        student.setVersion(-1);
        students.add(student);
        return students;
    }

    @HystrixCommand(commandProperties = {@HystrixProperty(name="execution.isolation.strategy", value="SEMAPHORE")})
//    @HystrixCommand
    public StudentVo findStudentById(Long classId,Long studentId){
        List<Student> students = findStudentsByClassId(classId);
        Student student = null;
        for(Student student1:students){
            if(student1.getId().equals(studentId))
                student = student1;
        }
        StudentVo studentVo = studentToVoConverter.convert(student);
//        ClassInfo classInfo = classInfoFeignClient.getClassInfo(classId);
        ClassInfo classInfo = classInfoRestTemplateClient.getClassInfo(classId);
        studentVo.setClassName(classInfo.getName());
        return studentVo;
    }

    private void randomlyRunLong(){
        Random rand = new Random();
        int randomNum = rand.nextInt((3 - 1) + 1) + 1;
        if (randomNum==3)
            sleep();
    }

    private void sleep(){
        try {
            Thread.sleep(11000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

}
