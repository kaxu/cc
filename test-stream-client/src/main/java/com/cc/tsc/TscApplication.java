package com.cc.tsc;

import com.cc.tsc.model.ClassInfoChangeModel;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.stream.annotation.EnableBinding;
import org.springframework.cloud.stream.annotation.StreamListener;
import org.springframework.cloud.stream.messaging.Sink;

@SpringBootApplication
@EnableBinding(Sink.class)
public class TscApplication {


    private static final Logger logger = LoggerFactory.getLogger(TscApplication.class);


    @StreamListener(Sink.INPUT)
    public void loggerSink(ClassInfoChangeModel infoChangeModel) {
        logger.debug("Received an event for class id {}", infoChangeModel.getClassInfoId());
    }
    public static void main(String[] args) {
        SpringApplication.run(TscApplication.class, args);
    }

}
