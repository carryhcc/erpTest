package com.example.config;

import com.baomidou.mybatisplus.annotation.DbType;
import com.baomidou.mybatisplus.extension.plugins.MybatisPlusInterceptor;
import com.baomidou.mybatisplus.extension.plugins.inner.OptimisticLockerInnerInterceptor;
import com.baomidou.mybatisplus.extension.plugins.inner.PaginationInnerInterceptor;
import org.mybatis.spring.annotation.MapperScan;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.transaction.annotation.EnableTransactionManagement;

/**
 * Created by IntelliJ IDEA.
 * Spring boot方式
 * EnableTransactionManagement  自动管理事务
 * Configuration  配置类注解
 * @author : cchu
 * Date: 2022/1/6 11:14
 */
@EnableTransactionManagement
@Configuration
public class MybatisPlusConfig {


    /**
     * Mybatis Plus 分页
     */
    @Bean
    public MybatisPlusInterceptor mybatisPlusInterceptor() {
        MybatisPlusInterceptor interceptor = new MybatisPlusInterceptor();
        //默认数据库类型
        interceptor.addInnerInterceptor(new PaginationInnerInterceptor(DbType.ORACLE_12C));
        //乐观锁
        interceptor.addInnerInterceptor(new OptimisticLockerInnerInterceptor());
        //分页配置
        interceptor.addInnerInterceptor(new PaginationInnerInterceptor());
        return interceptor;
    }
}
