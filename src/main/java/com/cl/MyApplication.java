package com.cl;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.builder.SpringApplicationBuilder;
import org.springframework.boot.web.support.SpringBootServletInitializer;
import org.springframework.transaction.annotation.EnableTransactionManagement;

/**
 * Created by cl on 2017/8/14.
 */

@SpringBootApplication
@EnableTransactionManagement//开启事务管理
public class MyApplication extends SpringBootServletInitializer{
    public static void main(String[] args) throws Exception {
        SpringApplication.run(MyApplication.class, args);
    }

    public SpringApplicationBuilder config(SpringApplicationBuilder applicationBuilder){
        return applicationBuilder.sources(MyApplication.class);
    }


}
