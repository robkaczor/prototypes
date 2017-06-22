package com.events.testservice;

import org.glassfish.jersey.servlet.ServletContainer;
import org.glassfish.jersey.servlet.ServletProperties;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.boot.context.embedded.ServletRegistrationBean;
import org.springframework.boot.context.web.SpringBootServletInitializer;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.transaction.annotation.EnableTransactionManagement;

/**
 *
 * Copyright (c) 2014 Events.com. <br/>
 * All Rights Reserved.
 */
@EnableAutoConfiguration
@Configuration
@ComponentScan
@EnableTransactionManagement
public class ApplicationConfig extends SpringBootServletInitializer {

    public static final String BASE_PATH = "/testservice/rest/*";
    
    public static void main(String[] args) {
        SpringApplication.run(ApplicationConfig.class, args);
    }
    
    @Bean
    public ServletRegistrationBean jerseyServlet() {
        ServletRegistrationBean registration = new ServletRegistrationBean(new ServletContainer(),
                BASE_PATH);
        registration.addInitParameter(ServletProperties.JAXRS_APPLICATION_CLASS, JerseyConfig.class.getName());
        return registration;
    }
}
