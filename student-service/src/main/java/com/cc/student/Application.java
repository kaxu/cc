package com.cc.student;

import com.cc.student.events.models.ClassInfoChangeModel;
import com.cc.student.utils.UserContextInterceptor;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.circuitbreaker.EnableCircuitBreaker;
import org.springframework.cloud.client.loadbalancer.LoadBalanced;
import org.springframework.cloud.context.config.annotation.RefreshScope;
import org.springframework.cloud.netflix.eureka.EnableEurekaClient;
import org.springframework.cloud.netflix.feign.EnableFeignClients;
import org.springframework.cloud.stream.annotation.EnableBinding;
import org.springframework.cloud.stream.annotation.StreamListener;
import org.springframework.cloud.stream.messaging.Sink;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Primary;
import org.springframework.http.client.ClientHttpRequestInterceptor;
import org.springframework.security.oauth2.client.OAuth2ClientContext;
import org.springframework.security.oauth2.client.OAuth2RestTemplate;
import org.springframework.security.oauth2.client.resource.OAuth2ProtectedResourceDetails;
import org.springframework.security.oauth2.config.annotation.web.configuration.EnableResourceServer;
import org.springframework.web.client.RestTemplate;

import java.util.Collections;
import java.util.List;

@SpringBootApplication
@RefreshScope
@EnableEurekaClient
@EnableFeignClients
@EnableCircuitBreaker
@EnableResourceServer
@EnableBinding(Sink.class)
public class Application {

    private static final Logger logger = LoggerFactory.getLogger(Application.class);

//    @Bean
//    public OAuth2RestTemplate oauth2RestTemplate(
//            OAuth2ClientContext oauth2ClientContext,
//            OAuth2ProtectedResourceDetails details) {
//        return new OAuth2RestTemplate(details, oauth2ClientContext);
//    }

    @StreamListener(Sink.INPUT)
    public void loggerSink(ClassInfoChangeModel infoChangeModel) {
        logger.debug("Received an event for class id {}", infoChangeModel.getClassInfoId());
    }

    @LoadBalanced
    @Bean
    public RestTemplate getCustomRestTemplate() {
        RestTemplate template = new RestTemplate();
        List interceptors = template.getInterceptors();
        if (interceptors == null) {
            template.setInterceptors(Collections.<ClientHttpRequestInterceptor>singletonList(new UserContextInterceptor()));
        } else {
            interceptors.add(new UserContextInterceptor());
            template.setInterceptors(interceptors);
        }

        return template;
    }

    public static void main(String[] args) {
        SpringApplication.run(Application.class, args);
    }
}
