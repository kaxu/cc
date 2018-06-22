package com.cc.classes.events.source;

import com.cc.classes.events.models.ClassInfoChangeModel;
import com.cc.classes.utils.UserContext;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cloud.stream.messaging.Source;
import org.springframework.messaging.support.MessageBuilder;
import org.springframework.stereotype.Component;


@Component
public class SimpleSourceBean {
    private Source source;

    private static final Logger logger = LoggerFactory.getLogger(SimpleSourceBean.class);

    @Autowired
    public SimpleSourceBean(Source source){
        this.source = source;
    }

    public void publishOrgChange(String action,Long classInfoId){
       logger.debug("Sending Kafka message {} for classInfoId Id: {}", action, classInfoId);
        ClassInfoChangeModel change =  new ClassInfoChangeModel(
                ClassInfoChangeModel.class.getTypeName(),
                action,
                classInfoId,
                UserContext.getCorrelationId());

        source.output().send(MessageBuilder.withPayload(change).build());
    }
}
