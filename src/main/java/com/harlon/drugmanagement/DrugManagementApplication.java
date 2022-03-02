package com.harlon.drugmanagement;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.quartz.QuartzDataSource;

@SpringBootApplication
public class DrugManagementApplication {

    public static void main(String[] args) {
        SpringApplication.run(DrugManagementApplication.class, args);
    }

}
