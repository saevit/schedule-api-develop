package com.example.scheduleapidevelop;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.data.jpa.repository.config.EnableJpaAuditing;

@SpringBootApplication
@EnableJpaAuditing
public class ScheduleApiDevelopApplication {

    public static void main(String[] args) {
        SpringApplication.run(ScheduleApiDevelopApplication.class, args);
    }

}
