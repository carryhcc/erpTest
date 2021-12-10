package com.example;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.jdbc.DataSourceAutoConfiguration;
import org.springframework.scheduling.annotation.EnableAsync;
import org.springframework.scheduling.annotation.EnableScheduling;

/**
 * @author cchu
 */
@SpringBootApplication(exclude = {
        DataSourceAutoConfiguration.class
})
@EnableScheduling
@EnableAsync(proxyTargetClass = true)
public class ErpTestApplication {

    public static void main(String[] args) {
        SpringApplication.run(ErpTestApplication.class, args);
        System.out.println("--启动成功--");
    }
}
