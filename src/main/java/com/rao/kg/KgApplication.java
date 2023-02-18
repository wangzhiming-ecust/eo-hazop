package com.rao.kg;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.scheduling.annotation.EnableScheduling;


@EnableScheduling
@SpringBootApplication
public class KgApplication {

    public static void main(String[] args) {
        SpringApplication.run(KgApplication.class, args);
    }

}
