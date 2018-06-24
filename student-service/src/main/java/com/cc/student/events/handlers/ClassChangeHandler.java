package com.cc.student.events.handlers;

import com.cc.student.events.CustomChannels;
import com.cc.student.events.models.ClassInfoChangeModel;
import com.cc.student.repository.ClassInfoRedisRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cloud.stream.annotation.EnableBinding;
import org.springframework.cloud.stream.annotation.StreamListener;


@EnableBinding(CustomChannels.class)
public class ClassChangeHandler {


    private static final Logger logger = LoggerFactory.getLogger(ClassChangeHandler.class);

//    @Autowired
//    private ClassInfoRedisRepository  classInfoRedisRepository;

    @StreamListener("inboundClassInfoChanges")
    public void loggerSink(ClassInfoChangeModel classChange) {
        logger.debug("Received a message of type " + classChange.getType());
        switch(classChange.getAction()){
            case "GET":
                logger.debug("Received a GET event from the organization service for organization id {}", classChange.getClassInfoId());
                break;
            case "SAVE":
                logger.debug("Received a SAVE event from the organization service for organization id {}", classChange.getClassInfoId());
                break;
            case "UPDATE":
                logger.debug("Received a UPDATE event from the organization service for organization id {}", classChange.getClassInfoId());
//                classInfoRedisRepository.deleteClassInfo(classChange.getClassInfoId());
                break;
            case "DELETE":
                logger.debug("Received a DELETE event from the organization service for organization id {}", classChange.getClassInfoId());
                break;
            default:
                logger.error("Received an UNKNOWN event from the organization service of type {}", classChange.getType());
                break;

        }
    }

}
