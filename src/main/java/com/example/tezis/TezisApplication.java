package com.example.tezis;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.scheduling.annotation.EnableScheduling;

@SpringBootApplication
@EnableScheduling
public class TezisApplication {

    public static void main(String[] args) {
        SpringApplication.run(TezisApplication.class, args);
    }

}
