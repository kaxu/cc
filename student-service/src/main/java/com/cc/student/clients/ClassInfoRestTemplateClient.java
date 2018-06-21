package com.cc.student.clients;

import com.cc.student.model.ClassInfo;
import com.cc.student.utils.UserContext;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.security.oauth2.client.OAuth2RestTemplate;
import org.springframework.stereotype.Component;

@Component
public class ClassInfoRestTemplateClient {
    @Autowired
    OAuth2RestTemplate restTemplate;

    private static final Logger logger = LoggerFactory.getLogger(ClassInfoRestTemplateClient.class);

    public ClassInfo getClassInfo(Long classId){
        logger.debug("In Students Service.getClassInfo: {}", UserContext.getCorrelationId());

        ResponseEntity<ClassInfo> restExchange =
                restTemplate.exchange(
                        "http://localhost:5555/api/classsrv/v1/classes/{classId}",
                        HttpMethod.GET,
                        null, ClassInfo.class, classId);

        return restExchange.getBody();
    }
}
