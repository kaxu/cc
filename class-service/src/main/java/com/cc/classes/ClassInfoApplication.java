package com.cc.classes;

import com.cc.classes.utils.UserContextFilter;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.netflix.eureka.EnableEurekaClient;
import org.springframework.cloud.stream.annotation.EnableBinding;
import org.springframework.cloud.stream.messaging.Source;
import org.springframework.context.annotation.Bean;
import org.springframework.security.oauth2.config.annotation.web.configuration.EnableResourceServer;

import javax.servlet.Filter;


@SpringBootApplication
@EnableEurekaClient
//@EnableFeignClients
@EnableResourceServer
//tells Spring Cloud Stream that you want to bind the service to a message broker
@EnableBinding(Source.class)
public class ClassInfoApplication {

    /*@Bean
    public Filter userContextFilter() {
        UserContextFilter userContextFilter = new UserContextFilter();
        return userContextFilter;
    }*/

    public static void main(String[] args) {
        SpringApplication.run(ClassInfoApplication.class, args);
    }
}
