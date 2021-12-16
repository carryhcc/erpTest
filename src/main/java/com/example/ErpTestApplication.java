package com.example;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

/**
 * @author cchu
 */
@SpringBootApplication
@MapperScan({"com.example.mapper"})
public class ErpTestApplication {

    public static void main(String[] args) {
        SpringApplication.run(ErpTestApplication.class, args);
        System.out.println("--启动成功--");
    }
}
