package com.cc.student.clients;

import com.cc.student.model.ClassInfo;
import com.cc.student.repository.ClassInfoRedisRepository;
import com.cc.student.utils.UserContext;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestTemplate;

@Component
public class ClassInfoRestTemplateClient {
//    @Autowired
//    OAuth2RestTemplate restTemplate;

    @Autowired
    RestTemplate restTemplate;

    @Autowired
    private ClassInfoRedisRepository classInfoRepository;

    private static final Logger logger = LoggerFactory.getLogger(ClassInfoRestTemplateClient.class);

    private ClassInfo checkRedisCache(Long classInfoId) {
        try {
            return classInfoRepository.findClassInfo(classInfoId);
        }
        catch (Exception ex){
            logger.error("Error encountered while trying to retrieve classInfo {} check Redis Cache.  Exception {}", classInfoId, ex);
            return null;
        }
    }

    private void cacheClassInfoObject(ClassInfo classInfo) {
        try {
            classInfoRepository.saveClassInfo(classInfo);
        }catch (Exception ex){
            logger.error("Unable to cache classInfo {} in Redis. Exception {}", classInfo.getId(), ex);
        }
    }

    public ClassInfo getClassInfo(Long classId){
        logger.debug("In Students Service.getClassInfo: {}", UserContext.getCorrelationId());

        ClassInfo  classInfo = checkRedisCache(classId);

        if (classInfo!=null){
            logger.debug("I have successfully retrieved an classInfo {} from the redis cache: {}", classId, classInfo);
            return classInfo;
        }

        logger.debug("Unable to locate classInfo from the redis cache: {}.", classId);

        ResponseEntity<ClassInfo> restExchange =
                restTemplate.exchange(
                        "http://localhost:5555/api/classsrv/v1/classes/{classId}",
                        HttpMethod.GET,
                        null, ClassInfo.class, classId);

        /*Save the record from cache*/
        classInfo = restExchange.getBody();

        if (classInfo!=null) {
            cacheClassInfoObject(classInfo);
        }

        return classInfo;

    }
}
