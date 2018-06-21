package com.cc.student.clients;


import com.cc.student.model.ClassInfo;
import org.springframework.cloud.netflix.feign.FeignClient;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

@FeignClient("classservice")
public interface ClassInfoFeignClient {
    @RequestMapping(
            method= RequestMethod.GET,
            value="http://localhost:5555/api/classsrv/v1/classes/{classId}",
            consumes="application/json")
    ClassInfo getClassInfo(@PathVariable("classId") Long classId);
}
