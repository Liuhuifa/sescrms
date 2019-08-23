package com.sesc.rms;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.transaction.annotation.EnableTransactionManagement;

@SpringBootApplication
@MapperScan("com.sesc.rms.dao")
@EnableTransactionManagement
public class SescrmsApplication {

    public static void main(String[] args) {
        SpringApplication.run(SescrmsApplication.class, args);
    }

}
