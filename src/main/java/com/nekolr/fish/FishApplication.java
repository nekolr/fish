package com.nekolr.fish;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.transaction.annotation.EnableTransactionManagement;

@SpringBootApplication
@EnableTransactionManagement
public class FishApplication {

    public static void main(String[] args) {
        SpringApplication.run(FishApplication.class, args);
    }

}
