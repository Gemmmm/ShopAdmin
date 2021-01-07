package com.tc.shop;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import tk.mybatis.spring.annotation.MapperScan;

@SpringBootApplication
@MapperScan(basePackages = "com.tc.shop.dao")
public class ShopadminApplication {
    public static void main(String[] args) {
        SpringApplication.run(ShopadminApplication.class, args);
        System.out.println("http://localhost:8083/login");
    }

}
