package com.cc.student.events;


import org.springframework.cloud.stream.annotation.Input;
import org.springframework.messaging.SubscribableChannel;

public interface CustomChannels {
    @Input("inboundClassInfoChanges")
    SubscribableChannel orgs();
}
