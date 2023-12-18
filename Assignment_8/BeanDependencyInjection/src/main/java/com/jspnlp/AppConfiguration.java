package com.jspnlp;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class AppConfiguration {
    @Bean
    public Address addressBean() {
        return new Address();
    }

    @Bean
    public Student studentBean() {
        return new Student();
    }
}
